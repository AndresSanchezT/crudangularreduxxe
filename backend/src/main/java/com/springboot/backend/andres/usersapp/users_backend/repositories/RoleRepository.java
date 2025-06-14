package com.springboot.backend.andres.usersapp.users_backend.repositories;

import com.springboot.backend.andres.usersapp.users_backend.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
