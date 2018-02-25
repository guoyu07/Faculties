import {Component, OnInit} from "@angular/core";
import {UserService} from "../../../services/user.service";
import {User} from "../../../models/User";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers: []
})
export class UserComponent implements OnInit {
  user: User;
  id: number;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.findById(this.id);
  }

  findById(id: number) {
    this.userService.findById(this.id).subscribe(
      user => {
        console.log(this.user);
        this.user = user;
        console.log(this.user);
      },
      err => {
        console.log(err);
      }
    );
  }
}
