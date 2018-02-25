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
import {UserComponent} from './user/user.component';
import {JwtService} from "../../services/jwt.service";

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  declarations: [UserListComponent,
    UserComponent,
    LoginComponent,
    RegisterComponent,
    StatusComponent],
  providers: [AuthService, UserService, JwtService]
})
export class UserModule {
}
