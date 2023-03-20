import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {DataService} from "./data.service";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardAdminService {

  constructor(private dataService:DataService, private router:Router,private authService: AuthService) {}

  canActivate():boolean{
    if( this.dataService.role=='admin' && this.authService.isLoggedIn()){
      return true;
    }else {
      this.router.navigate(['login']);
      return false;
    }
  }
}
