import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import {User} from '../models/User';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class UserService {

  private apiUrl = 'http://localhost:9000/users';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('token')}`
  });

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<User[]> {
    return this.http.get(this.apiUrl, {headers: this.headers})
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findById(id: number): Observable<User> {
    return this.http.get(this.apiUrl + '/id/' + id, {headers: this.headers})
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  saveUser(user: User): Observable<User> {
    return this.http.post(this.apiUrl, user)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteUserById(id: number) {
    return this.http.delete(this.apiUrl + '/' + id, {headers: this.headers});
  }

  updateUser(user: User): Observable<User> {
    return null;
  }

}
