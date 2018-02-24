import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from './user-list/user-list.component';
import {EnsureAuthenticated} from '../../services/ensure-authenticated.service';
import {StatusComponent} from './status/status.component';
import {LoginRedirect} from '../../services/login-redirect.service';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {UserComponent} from "./user/user.component";

const routes: Routes = [
  {path: 'user', component: UserListComponent},
  // {path: 'user/create', component: UserCreateComponent},
  {path: 'user/:id', component: UserComponent},
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [LoginRedirect]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [LoginRedirect]
  },
  {
    path: 'profile',
    component: StatusComponent,
    canActivate: [EnsureAuthenticated]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class UserRoutingModule {
}
