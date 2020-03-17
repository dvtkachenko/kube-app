import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent, NotFoundComponent, AboutComponent, LoginComponent, RegisterComponent } from './components';
import { AuthService, AuthGuard } from './services';
import { NgxSpinnerModule } from 'ngx-spinner';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    NgxSpinnerModule
  ],
  declarations: [
    NavbarComponent,
    NotFoundComponent,
    AboutComponent,
    LoginComponent,
    RegisterComponent
  ],
  exports: [
    NavbarComponent,
    NotFoundComponent,
    AboutComponent,
    LoginComponent,
    RegisterComponent    
  ],
  providers: [
    AuthService, AuthGuard
  ],
})
export class CoreModule { }
