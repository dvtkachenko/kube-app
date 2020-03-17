import { Routes } from '@angular/router';
import { LoginComponent, RegisterComponent, AboutComponent, NotFoundComponent } from './components';
import { AuthGuard } from './services';

export const coreRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'about', component: AboutComponent, canActivate: [ AuthGuard ] },
    { path: '**', component: NotFoundComponent, canActivate: [ AuthGuard ] }
]; 