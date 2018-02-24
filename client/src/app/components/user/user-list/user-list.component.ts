import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../services/user.service';
import {User} from '../../../models/dto/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: [UserService]
})
export class UserListComponent implements OnInit {

  public users: User[];


  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.findAll().subscribe(
      users => {
        this.users = users;
        console.log(this.users);
      },
      err => {
        console.log(err);
      }
    );
  }

  deleteUser(user: User) {
    if (user) {
      this.userService.deleteUserById(user.id).subscribe(
        res => {
          this.users = this.users.filter(function (item) {
            return item !== user;
          });
          console.log('done');
        }
      );
    }
  }

  getProfile(user: User) {
    if (user) {
      this.router.navigate(['user/', user.id]);
      console.log(user);
    }
  }

}
