import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';
import {JwtService} from "./jwt.service";

@Injectable()
export class EnsureAuthenticated implements CanActivate {
  constructor(private auth: JwtService, private router: Router) {}
  canActivate(): boolean {
    if (this.auth.isExists()) {
      return true;
    } else {
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
