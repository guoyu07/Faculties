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

import {GroupModule} from './components/group/group.module';

import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import {IndexModule} from "./index/index.module";
import {JwtService} from "./services/jwt.service";

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    UserModule,
    RouterModule,
    GroupModule,
    IndexModule
  ],
  providers: [
    EnsureAuthenticated,
    LoginRedirect,
    HttpClientModule,
    JwtService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
