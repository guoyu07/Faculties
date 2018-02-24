import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GroupListComponent} from './group-list/group-list.component';
import {GroupComponent} from './group/group.component';

const routes: Routes = [
  {path: 'group', component: GroupListComponent},
  // {path: 'user/create', component: UserCreateComponent},
  {path: 'group/:id', component: GroupComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class GroupRoutingModule {
}
