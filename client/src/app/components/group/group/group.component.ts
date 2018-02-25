import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Group} from '../../../models/Group';
import {GroupService} from '../../../services/group.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  group: Group = new Group();
  id: number;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private groupService: GroupService) {
  }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.findById(this.id);
  }

  findById(id: number) {
    this.groupService.findById(this.id).subscribe(
      group => {
        this.group = group;
        console.log(this.group);
      },
      err => {
        console.log(err);
      }
    );
  }

}
