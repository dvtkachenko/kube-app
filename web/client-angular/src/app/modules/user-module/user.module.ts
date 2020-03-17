import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent, UserEditComponent, UserAddComponent } from './components';
import { UserService } from './services';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    NgxSpinnerModule
  ],
  declarations: [
    UserListComponent,
    UserEditComponent,
    UserAddComponent
  ],
  exports: [
    UserListComponent,
    UserEditComponent,
    UserAddComponent
  ],
  providers: [ UserService ],
})
export class UserModule { }
