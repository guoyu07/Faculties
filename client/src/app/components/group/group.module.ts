import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import {GroupRoutingModule} from "./group-routing.module";
import {GroupComponent} from "./group/group.component";
import {GroupListComponent} from "./group-list/group-list.component";
import {GroupService} from "../../services/group.service";

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

  providers: [GroupService]
})
export class GroupModule {
}
