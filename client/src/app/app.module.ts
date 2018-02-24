import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/user/login/login.component';
import { AuthService } from './services/auth.service';
import { RegisterComponent } from './components/user/register/register.component';
import { StatusComponent } from './components/user/status/status.component';
import { EnsureAuthenticated } from './services/ensure-authenticated.service';
import { LoginRedirect } from './services/login-redirect.service';
import {UserModule} from "./components/user/user.module";
import {HttpClientModule} from "@angular/common/http";
import { UserComponent } from './components/user/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    UserModule,
    RouterModule
  ],
  providers: [
    EnsureAuthenticated,
    LoginRedirect,
    HttpClientModule,
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
