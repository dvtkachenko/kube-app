import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

// children routes
import { showPipesDirectivesRoutes, userRoutes } from './modules';
import { coreRoutes, AuthGuard } from './core';

const routes: Routes = [
  { path: '', children: [...userRoutes], canActivate: [ AuthGuard ] },
  { path: '', children: [...showPipesDirectivesRoutes], canActivate: [ AuthGuard ] },
  { path: '', children: [...coreRoutes] }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class RoutingModule { }
