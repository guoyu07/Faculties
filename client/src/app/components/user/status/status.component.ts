import {Component, OnInit} from "@angular/core";
import {AuthService} from "../../../services/auth.service";
import {User} from "../../../models/User";
import {Router} from "@angular/router";

@Component({
  selector: 'status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {
  user: User = new User;
  error: {};

  constructor(private router: Router, private auth: AuthService) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      console.log(token);
      this.auth.ensureAuthenticated(token)
        .then((user) => {
          console.log(user.json());
          this.user = user.json();
        })
        .catch((err) => {
          this.error = err;
        });
    }
  }
}
