import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CounterComponent } from './counter/counter.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CounterComponent,CommonModule,RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  
  title: string = 'hola mundo angular desde componente!'

  users: string[] = ['Pepe','Maria','Juan','Andres']

  subTitle = 'Contador con estado de session'

  visible:boolean = false

  counterPadre = 0

  ngOnInit(): void {
    this.counterPadre = parseInt(sessionStorage.getItem("counter")!) || 0
  }

  setVisible():void {
    this.visible = this.visible? false : true
    console.log("hemos hecho click en setVisible")

  }

  setCounterPadre(counter:number):void {
    this.counterPadre = counter
  }

  
}
