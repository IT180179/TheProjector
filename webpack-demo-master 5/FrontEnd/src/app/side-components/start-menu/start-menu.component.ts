import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {LoginComponent} from "../../login/login.component";

@Component({
  selector: 'app-start-menu',
  templateUrl: './start-menu.component.html',
  styleUrls: ['./start-menu.component.scss']
})
export class StartMenuComponent implements OnInit {

  constructor(public loginComponent: LoginComponent, private router: Router, private snackBar: MatSnackBar, public data: DataService, public service: HttpService) {}

  ppk: any;
  isLoggedIn: any
  recht: any = 2;
  disablePPK: boolean = true

  ngOnInit():void {
    if(!this.data.isloggedIn){
      this.router.navigate(['**']);
    }
    // console.log(this.data.role)
    this.recht = this.data.recht;
    this.isLoggedIn = this.data.isloggedIn
    this.ppk = this.service.getPPK().subscribe({
      next: value => {
        // console.log(value)
        this.ppk = value
      }, error: err => {}
    });
  }
}
