import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {EnsureAuthenticated} from './services/ensure-authenticated.service';
import {LoginRedirect} from './services/login-redirect.service';
import {UserModule} from './components/user/user.module';
import {HttpClientModule} from '@angular/common/http';
import {UserComponent} from './components/user/user/user.component';
import { GroupComponent } from './components/group/group/group.component';
import { GroupListComponent } from './components/group/group-list/group-list.component';
import {GroupModule} from './components/group/group.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    UserModule,
    RouterModule,
    GroupModule
  ],
  providers: [
    EnsureAuthenticated,
    LoginRedirect,
    HttpClientModule,
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
