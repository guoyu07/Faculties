import {Component, OnInit} from "@angular/core";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {
  name: string;

  constructor(private auth: AuthService) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      console.log(token);
      this.auth.ensureAuthenticated(token)
        .then((user) => {
          console.log(user.json());
          this.name = user.json().name;
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }
}
