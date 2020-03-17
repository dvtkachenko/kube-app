import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from 'node_modules/ngx-spinner';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models';
import { UserService } from '../../services';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {

  public userAddForm: FormGroup;

  user: User;

  constructor(private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) { }

  ngOnInit() {
    this.user = {
      // id: 1,
      name: '',
      age: 18,
      comment: ''
    }
    this.userAddForm = this.createUserAddForm(this.user);
  }

  public onAdd(): void {

    this.spinner.show();

    if (this.userAddForm.invalid) {
      return;
    }

    // this.user.id = this.userAddForm.value.id;
    this.user.name = this.userAddForm.value.name;
    this.user.age = this.userAddForm.value.age;
    this.user.comment = this.userAddForm.value.comment;

    const clonedTodo = Object.assign({}, this.user);

    this.userService.createUser(clonedTodo)
      .subscribe((response: User) => {
        this.spinner.hide();
        this.toastr.success("User was successfully created", "Info", { timeOut: 3000 });
        this.router.navigate(['/']);
      }, error => {
        this.spinner.hide();
        this.toastr.error("Can not create user on server", "Error", { timeOut: 3000 });
      });
  }

  public onCancel(): void {
    this.router.navigate(['/']);
  }
 
  private createUserAddForm(user: User): FormGroup {
    return this.fb.group({
      id: [user.id],
      name: [user.name, [Validators.required]],
      age: [user.age, [Validators.required]],
      comment: [user.comment]
    });
  } 
}
