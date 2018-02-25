import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";
import {User} from "../../../models/Request";
import {JwtService} from "../../../services/jwt.service";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',

  styleUrls: ['./login.component.css'],
  providers: []
})
export class LoginComponent {
  user: User = new User();
  error: string;

  constructor(private router: Router, private auth: AuthService, private jwtService: JwtService) {
  }

  onLogin(): void {
    this.auth.login(this.user)
      .then((user) => {
        this.jwtService.saveToken(user.json().token);
        this.router.navigateByUrl('/profile');
      })
      .catch((err) => {
        console.log(err);
        this.error = 'Username or password was incorrect. Please try again.';
      });
  }
}
