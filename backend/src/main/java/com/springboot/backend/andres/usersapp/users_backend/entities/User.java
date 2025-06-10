package com.springboot.backend.andres.usersapp.users_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.backend.andres.usersapp.users_backend.models.IUser;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements IUser{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    @Transient
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) --> hace que el atributo admin no se implemente en el JSON de salida (no serializa este atributo para que sea transformado de clase JAVA a Json), pero si puede recibir valores de un JSON entrante en el cuerpo en una solicitud(si deserializa el valor entrante del atributo para que se acople al objeto JAVA).
    private boolean admin;

    @NotBlank
    private String password;

    @JsonIgnoreProperties({"handler","hibernateLazyInitializer"}) //-->> Esta anotación se utiliza para evitar que los campos "handler" y "hibernateLazyInitializer" sean serializados a JSON, ya que hibernate mediante la carga peresoza(fetch = FetchType.LAZY) genera estos campos para manejar la carga diferida, pero puede dar error al serializar el objeto
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}
    )
    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
