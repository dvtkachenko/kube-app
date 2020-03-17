import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShowPipesDirectivesComponent } from './components/show-pipes-directives';
import { CustomDatePipe, SumArrayPipe } from './pipes';
import { MyNgStyleDirective, MyNgClassDirective } from './directives';

@NgModule({
  imports: [
    CommonModule,
    RouterModule
  ],
  declarations: [
    ShowPipesDirectivesComponent,
    CustomDatePipe,
    SumArrayPipe,
    MyNgStyleDirective,
    MyNgClassDirective
  ],
  exports: [
    ShowPipesDirectivesComponent  
  ],
  providers: [],
})
export class ShowPipesDirectivesModule { }
