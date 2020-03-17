import { ToastrService } from 'ngx-toastr';
import { NgxSpinnerService } from 'ngx-spinner';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models';
import { UserService } from '../../services';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService,
    private spinner: NgxSpinnerService,
    private toastr: ToastrService) { }

  ngOnInit() {

    this.spinner.show();

    this.userService.getUsers()
      .subscribe((response: User[]) => {
        this.users = response;
        this.spinner.hide()
      }, error => {
        this.spinner.hide();
        this.toastr.error("Can not get users from server", "Error", { timeOut: 3000 });
      }
      );
  }

  public onDelete(id: number): void {

    this.spinner.show();

    this.userService.deleteUser(id)
      .subscribe((response: Object) => {
        this.users = this.users.filter(filteredUsers => filteredUsers.id != id);
        this.spinner.hide();
        this.toastr.success("User was successfully deleted", "Info", { timeOut: 3000 });
      }, error => {
        this.spinner.hide();
        this.toastr.error("User was not deleted", "Error", { timeOut: 3000 });
      }
        // in an error case it does not work
        // () => this.spinner.hide()    
      );
  }

}
