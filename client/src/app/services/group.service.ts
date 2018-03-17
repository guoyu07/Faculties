import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import {User} from '../models/User';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Group} from '../models/Group';


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
    return this.http.get(this.apiUrl) .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findById(id: number): Observable<Group> {
    return this.http.get(this.apiUrl + '/' + id) .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  saveGroup(group: Group): Observable<Object> {
    return this.http.post(this.apiUrl, group, {headers: this.headers})
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteById(id: number) {
    return this.http.delete(this.apiUrl + '/' + id, {headers: this.headers});
  }

  updateGroup(group: Group): Observable<void> {
    return null;
  }

}
