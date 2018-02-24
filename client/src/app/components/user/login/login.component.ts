import {Component} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";
import {User} from "../../../models/user";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',

  styleUrls: ['./login.component.css'],
  providers: []
})
export class LoginComponent {
  user: User = new User();

  constructor(private router: Router, private auth: AuthService) {
  }

  onLogin(): void {
    localStorage.clear();
    this.auth.login(this.user)
      .then((user) => {
        localStorage.setItem('token', user.json().token);
        this.router.navigateByUrl('/profile');
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
