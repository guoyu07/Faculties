import {Component, OnInit} from "@angular/core";
import {JwtService} from "../../services/jwt.service";
import {getToken} from "codelyzer/angular/styles/cssLexer";
import {isUndefined} from "util";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAdmin: boolean;
  private jwtService: JwtService;
  private token: string;

  constructor(jwtService: JwtService) {
    this.jwtService = jwtService;

  }
  ngOnInit() {
    this.token = this.getToken();
    this.isAdmin = this.jwtService.isAdmin();
  }

  getToken() {
    this.isAdmin = this.jwtService.isAdmin();
    return this.jwtService.isExists();
  }

  logout() {
    this.isAdmin = false;
    this.jwtService.destroyToken();
  }
}
