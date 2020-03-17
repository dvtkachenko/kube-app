import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService, AUTH } from '../../services';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public brand: string = "Todos";

  public isShowed: boolean = false;

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    this.authService.authEvent
      .subscribe((authStatus: boolean) => {
        if (authStatus == AUTH) {
          this.isShowed = true;
        } else {
          this.isShowed = false;
        }
      });
  }

  public logout() {
    this.authService.logout();
    this.router.navigate(['login']);      
  }
}
