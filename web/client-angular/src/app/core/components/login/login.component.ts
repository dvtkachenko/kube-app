import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;

  constructor(private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) { }

  ngOnInit() {
    if (this.authService.isAuth()) {
      this.router.navigate(['/']);      
    }
    this.loginForm = this.createLoginForm();
  }

  public onSubmit(): void {

    if (this.loginForm.invalid) {
      return;
    }

    this.spinner.show();

    this.authService.login(this.loginForm.value.email, this.loginForm.value.password)
      .subscribe((response: boolean) => {
        this.spinner.hide();
        this.router.navigate(['/']);
      }, ({error, status}) => {
        this.spinner.hide();
        this.toastr.error(`Can not login ! ${error} ! Status code: ${status}`, "Error", { timeOut: 3000 });
      });
  }

  public onRegister(): void {
    this.router.navigate(['register']);
  }

  private createLoginForm(): FormGroup {
    return this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

}
