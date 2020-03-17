import { Routes } from '@angular/router';
import { UserListComponent, UserAddComponent, UserEditComponent } from './components';

export const userRoutes: Routes = [
    { path: '', component: UserListComponent },
    { path: 'users', component: UserAddComponent },
    { path: 'users/:id', component: UserEditComponent }
]; 