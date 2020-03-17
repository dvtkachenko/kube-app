import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models';

@Injectable()
export class UserService {

  private restApiUrl: string = environment.rest_api_url;

  constructor(private httpClient: HttpClient) { 
  }

  public getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.restApiUrl}/users`);
  }

  public getUser(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.restApiUrl}/users/${id}`);
  }  
  
  public createUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.restApiUrl}/users`, user);
  }

  public updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.restApiUrl}/users`, user);
  }

  public deleteUser(id: number): Observable<Object> {
    return this.httpClient.delete<Object>(`${this.restApiUrl}/users/${id}`);
  }    
}
