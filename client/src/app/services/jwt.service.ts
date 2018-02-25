import {Injectable} from "@angular/core";
import {isUndefined} from "util";


@Injectable()
export class JwtService {

  getToken(): String {
    return localStorage.getItem('token');
  }

  saveToken(token: String) {
    localStorage.setItem('token', token);
    const jwtData = token.split('.')[1];
    const decodedJwtJsonData = window.atob(jwtData);
    const decodedJwtData = JSON.parse(decodedJwtJsonData);
    localStorage.setItem('roles', decodedJwtData.roles);
  }

  destroyToken() {
    localStorage.clear();
  }

  isExists() {
    if (localStorage.getItem('token') != null){
      return !isUndefined(localStorage.getItem('token'));
    }
    return false;
  }

  isAdmin() {
    if (localStorage.getItem('roles') != null){
      return localStorage.getItem('roles').includes('ADMIN');
    }
    return false;
  }

  isUser() {
    if (localStorage.getItem('roles') != null){
      return localStorage.getItem('roles').includes('USER');
    }
    return false;
  }

  isVerified() {
    if (localStorage.getItem('roles') != null){
      return localStorage.getItem('roles').includes('VERIFIED');
    }
    return false;
  }


}
