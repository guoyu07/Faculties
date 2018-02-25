import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/User';
import {UserService} from '../../../services/user.service';
import {Router} from '@angular/router';
import {Group} from '../../../models/Group';
import {GroupService} from '../../../services/group.service';
import {JwtService} from "../../../services/jwt.service";

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  isAdmin: boolean;
  public groups: Group[];


  constructor(private router: Router, private groupService: GroupService, private jwtService: JwtService) {
  }

  ngOnInit() {
    this.isAdmin = this.jwtService.isAdmin();
    this.getAllUsers();
  }

  getAllUsers() {
    this.groupService.findAll().subscribe(
      groups => {
        this.groups = groups;
        console.log(this.groups);
      },
      err => {
        console.log(err);
      }
    );
  }

  deleteGroup(group: Group) {
    if (group) {
      this.groupService.deleteById(group.id).subscribe(
        res => {
          this.groups = this.groups.filter(function (item) {
            return item !== group;
          });
          console.log('done');
        }
      );
    }
  }

  getGroup(group: Group) {
    if (group) {
      this.router.navigate(['group/', group.id]);
      console.log(group);
    }
  }

}
