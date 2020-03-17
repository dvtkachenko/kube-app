import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'node_modules/ngx-toastr';
import { NgxSpinnerService } from 'node_modules/ngx-spinner';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models';
import { UserService } from '../../services';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  public userEditForm: FormGroup;

  id: string;
  user: User;
  isReadOnly: boolean = true;

  constructor(private fb: FormBuilder, 
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) { }

  ngOnInit() {

    this.userEditForm = this.createUserEditForm();
    // this.userEditForm.get("comment").disable();

    this.spinner.show();

    this.id = this.activatedRoute.snapshot.params['id'];
    this.userService.getUser(this.id)
      .subscribe((response: User) => {
        this.user = response;
        this.spinner.hide();
        this.initUserEditForm(this.user);
      }, error => {
        this.toastr.error("Can not get user from server", "Error", { timeOut: 3000 });
        this.router.navigate(['/']);
        this.spinner.hide();
      }
      );
  }

  public onEdit(): void {
    this.isReadOnly = false;
    // this.userEditForm.get("comment").enable();
  }

  public onSave(): void {
    
    if (this.userEditForm.invalid) {
      return;
    }

    this.isReadOnly = true;
    // this.userEditForm.get("comment").disable();
    this.spinner.show();

    this.user.id = this.userEditForm.value.id;
    this.user.name = this.userEditForm.value.name;
    this.user.age = this.userEditForm.value.age;
    this.user.comment = this.userEditForm.value.comment;

    const clonedUser = Object.assign({}, this.user);

    this.userService.updateUser(clonedUser)
      .subscribe((response: User) => {
        this.user = response;
        this.spinner.hide();
        this.toastr.success("User was successfully updated", "Info", { timeOut: 3000 });
        this.router.navigate(['/']);
      }, error => {
        this.spinner.hide();
        this.toastr.error("Can not update user on server", "Error", { timeOut: 3000 });
      }
        // in an error case it does not work
        // () => {
        //   this.router.navigate(['/']);
        //   this.spinner.hide();
        // }
      );
  }

  public onCancel(): void {
    this.router.navigate(['/']);
  }
   
  private createUserEditForm(): FormGroup {
    return this.fb.group({
      id: [''],
      name: ['', [Validators.required]],
      age: ['', [Validators.required]],
      comment: ['']
    });
  } 
   
  private initUserEditForm(user: User): void {
    this.userEditForm.get("id").setValue(user.id);
    this.userEditForm.get("name").setValue(user.name);
    this.userEditForm.get("age").setValue(user.age);
    this.userEditForm.get("comment").setValue(user.comment);
  } 

}
