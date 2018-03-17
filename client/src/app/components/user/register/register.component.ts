import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../../services/auth.service';
import {User} from '../../../models/Request';

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: User = new User();

  constructor(private router: Router, private auth: AuthService) {
  }

  onRegister(): void {
    this.auth.register(this.user)
      .then((user) => {
        this.router.navigateByUrl('/');
      })
      .catch((err) => {
        console.log(err.json());
      });
  }
}
