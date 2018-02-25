import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {User} from "../models/Request";
import "rxjs/add/operator/toPromise";
import {JwtService} from "./jwt.service";

@Injectable()
export class AuthService {
  private BASE_URL: string = 'http://localhost:9000/auth';
  private headers: Headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http, private jwtService: JwtService) {
  }

  login(user: Object): Promise<any> {
    let url: string = `${this.BASE_URL}/login`;
    return this.http.post(url, user, {headers: this.headers}).toPromise();
  }

  logout() {
    this.jwtService.destroyToken();
  }

  register(user: User): Promise<any> {
    let url: string = `${this.BASE_URL}/register`;
    return this.http.post(url, user, {headers: this.headers}).toPromise();
  }

  ensureAuthenticated(token): Promise<any> {
    let url: string = `${this.BASE_URL}/me`;
    let headers: Headers = new Headers({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    });
    return this.http.get(url, {headers: headers}).toPromise();
  }
}
