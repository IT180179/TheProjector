import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {DataService} from "./services/data.service";
import {dashCaseToCamelCase} from "@angular/compiler/src/util";
import {LoginComponent} from "./login/login.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['../styles.scss']
})
export class AppComponent {

  constructor(private http: HttpClient, public dataService: DataService, public loginComponent:LoginComponent) {
  }
  loggedIn: any
  rechte = 1
  role: any;
  ngOnInit() {
    this.loggedIn = this.dataService.isloggedIn
    this.rechte = this.dataService.recht
    console.log(this.loginComponent.role)
    this.role = this.dataService.role;
  }

}
