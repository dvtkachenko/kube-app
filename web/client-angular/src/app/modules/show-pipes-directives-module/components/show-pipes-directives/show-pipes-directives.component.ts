import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-show-pipes-directives',
  templateUrl: './show-pipes-directives.component.html',
  styleUrls: ['./show-pipes-directives.component.css']
})
export class ShowPipesDirectivesComponent implements OnInit {

  public currentDate: number;

  public numArray: number[] = [];

  constructor() { }

  ngOnInit() {
    this.currentDate = new Date().getTime();
    this.numArray = [3, 5, 10];
  }

  public addNewElement(): void {
    this.numArray.push(Math.floor(Math.random() * 20));
  }
}
