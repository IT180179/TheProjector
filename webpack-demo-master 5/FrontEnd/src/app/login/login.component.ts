import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DataService} from "../services/data.service"
import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';
import {HttpService} from "../services/http.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  payload: any;
  role:any;
  username: any
  userdata:any

  constructor(private fb: FormBuilder, private authService: AuthService, private snackBar: MatSnackBar, private router: Router,public dataService: DataService, public http: HttpService) {
    this.loginForm = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }
  login(): void {
    const val = this.loginForm.value;
    this.authService.login(val.username, val.password).subscribe({
      next: value => {
        sessionStorage.setItem('id_token', value.token);
        sessionStorage.setItem('expires_at', value.expires_at);
        // console.log(value)
        const decodedToken = jwt_decode(value.token)
        // console.log(decodedToken);
        // @ts-ignore
        this.role = decodedToken.groups[0];
        // @ts-ignore
        this.username = decodedToken.upn;
        // console.log(this.username)
        // console.log(this.role)
        this.dataService.role = this.role

        this.userdata = this.http.searchUser(this.username).subscribe({
          next: value => {
            // console.log(value)
            this.userdata = value
            this.dataService.user_id = value[0].personen_id;
             // console.log(value[0].personen_id)
          }, error: err => {
            this.snackBar.open(`Daten konnten nicht geladen werden ${err.message}`, undefined, {
              duration: 3000,
              panelClass: 'snackbar-dark'
            });
          }
        });

        this.dataService.isloggedIn = true
        if(this.role == 'admin'){
          this.router.navigate(['/start_menu']);
        }else {
          this.router.navigate(['/menu'])
        }

      }, error: err => {
        this.dataService.isloggedIn = false
        this.authService.logout();
        this.snackBar.open(`ANMELDUNG FEHLGESCHLAGEN: Versuchen Sie es erneut!`, undefined, { panelClass: 'snackbar-dark'});
      }, complete: () => {
        // console.log("Login Request is completed")
      }
    });


  }
  ngOnInit(): void {
  }
}
