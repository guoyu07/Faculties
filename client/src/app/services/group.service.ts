import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import {User} from '../models/dto/User';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Group} from '../models/dto/Group';


@Injectable()
export class GroupService {

  private apiUrl = 'http://localhost:9000/groups';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('token')}`
  });

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<Group[]> {
    return this.http.get(this.apiUrl, {headers: this.headers});
  }

  findById(id: number): Observable<Group> {
    return this.http.get(this.apiUrl + '/' + id, {headers: this.headers});
  }

  saveUser(group: Group): Observable<Object> {
    return this.http.post(this.apiUrl, group)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteUserById(id: number) {
    return this.http.delete(this.apiUrl + '/' + id, {headers: this.headers});
  }

  updateUser(group: Group): Observable<User> {
    return null;
  }

}
