import { ToastrService } from 'node_modules/ngx-toastr';
import { NgxSpinnerService } from 'node_modules/ngx-spinner';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl, ValidationErrors } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services';
import { User } from '../../models';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  public registerForm: FormGroup;

  constructor(private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) { }

  ngOnInit() {
    if (this.authService.isAuth()) {
      this.router.navigate(['/']);      
    }
    this.registerForm = this.createRegisterForm();
  }

  public onSubmit(): void {

    if (this.registerForm.invalid) {
      return;
    }

    const newUser: User = { email: this.registerForm.value.email, 
                            name: this.registerForm.value.name,
                            password: this.registerForm.value.password };

    this.spinner.show();

    this.authService.registerNewUser(newUser)
      .subscribe((response: boolean) => {
        this.spinner.hide();
        this.toastr.success("New user was successfully created !", "Info", { timeOut: 3000 });
        this.router.navigate(['/']);
      }, ({error, status}) => {
        this.spinner.hide();
        this.toastr.error(`Can not create new user ! ${error} ! Status code: ${status}`, "Error", { timeOut: 3000 });
      });
  }

  public onCancel(): void {
    this.router.navigate(['login']);
  }
  
  private nameValidator(control: FormControl): ValidationErrors {

    const value: string = control.value;

    const isLengthNotShort: boolean = value ? value.length > 2 : false;
    const isLengthNotLong: boolean = value ? value.length < 17 : false;

    let hasForbiddenLetter: boolean = false;
    value.split('').forEach(letter => { 
      if (value && !(/[a-zA-Z]/.test(letter))) {
        hasForbiddenLetter = true;
      }
    });

    const nameValid = isLengthNotShort && isLengthNotLong && !hasForbiddenLetter;
   
    if (!nameValid) {

      let errors = {};

      if (!isLengthNotShort) {
        errors['isLengthNotShort'] = "Name should consist more than 2 symbols !";
      }

      if (!isLengthNotLong) {
        errors['isLengthNotLong'] = "Name should consist less than 16 symbols !";
      }

      if (hasForbiddenLetter) {
        errors['hasForbiddenLetter'] = "Name should consist only english letters !";
      }

      return errors;
    }
     return null;
   }

  private passwordValidator(control: FormControl): ValidationErrors {

    const value = control.value;

    const isNotEmpty: boolean = value ? value.length > 0 : false;
    const hasNumber: boolean = /[0-9]/.test(value);
    const hasCapitalLetter: boolean = /[A-Z]/.test(value);
    const isLengthValid: boolean = value ? value.length > 7 : false;

    const passwordValid = isNotEmpty && hasNumber && hasCapitalLetter && isLengthValid;
   
    if (!passwordValid) {

      let errors = {};

      if (!isNotEmpty) {
        errors['isNotEmpty'] = "Password can not be empty !";
      }

      if (!hasNumber) {
        errors['hasNoNumber'] = "Password should consist at least 1 number !";
      }

      if (!hasCapitalLetter) {
        errors['hasNoCapitalLetter'] = "Password should consist at least 1 capital letter !";
      }

      if (!isLengthValid) {
        errors['lengthInvalid'] = "Password should consist at least 8 symbols !";
      }

      return errors;
    }
     return null;
   }

  private createRegisterForm(): FormGroup {
    return this.fb.group({
      email: ['', [ Validators.required, Validators.email ]],
      name: ['', [ this.nameValidator ]],
      password: ['', [ this.passwordValidator ]]
    });
  }
}
