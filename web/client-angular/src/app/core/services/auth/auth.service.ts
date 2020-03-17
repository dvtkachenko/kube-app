import { environment } from './../../../../environments/environment';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../models';

export const AUTH = true;
export const NOT_AUTH = false;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authStatus: BehaviorSubject<boolean>;

  public authEvent: Observable<boolean>;

  private authUrl: string = environment.auth_url;

  private _token: string;

  constructor(private httpClient: HttpClient) {
    this.authStatus = new BehaviorSubject(this.isAuth());
    this.authEvent = this.authStatus.asObservable();
  }

  public registerNewUser(user: User): Observable<boolean> {
    return this.httpClient.post(`${this.authUrl}/signup`, user, { responseType: "text"})
      .pipe(map( (response: string): boolean => { 
                  this.token = response;
                  this.authStatus.next(AUTH);
                  return true; 
                }) );
  }

  public login(email: string, password: string): Observable<boolean> {
    return this.httpClient.post(`${this.authUrl}/login`, { email, password }, { responseType: "text"})
      .pipe(map( (response: string): boolean => { 
                  this.token = response;
                  this.authStatus.next(AUTH);
                  return true; 
                }) );
  }

  public logout(): void {
    this.token = null;
    this.authStatus.next(NOT_AUTH);
  }

  public isAuth(): boolean {
    if (this._token || localStorage.getItem("token")) {
      return AUTH;
    } 
    return NOT_AUTH;
  }

  private set token(token: string) {
    this._token = token;
    if(token) {
      localStorage.setItem("token", token);
    } else {
      localStorage.removeItem('token');
    }
  }
}
