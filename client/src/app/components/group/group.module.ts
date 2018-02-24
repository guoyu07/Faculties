import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';
import {UserListComponent} from './user-list/user-list.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './login/login.component';
import {StatusComponent} from './status/status.component';
import {RegisterComponent} from './register/register.component';
import {AuthService} from '../../services/auth.service';
import {UserService} from '../../services/user.service';
import {GroupRoutingModule} from './group-routing.module';
import {GroupComponent} from './group/group.component';
import {GroupListComponent} from './group-list/group-list.component';
import {GroupService} from '../../services/group.service';

@NgModule({
  imports: [
    CommonModule,
    GroupRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  declarations: [GroupComponent,
    GroupListComponent],

  providers: [AuthService, UserService, GroupService]
})
export class GroupModule {
}
