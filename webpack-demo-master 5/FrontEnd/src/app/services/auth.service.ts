import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  SERVER_URL = 'http://localhost:8080/personen/login';

  constructor(private http: HttpClient, private router: Router) {
  }

  login(username: string, password: string): Observable<any> {
    // console.log("Passwort: " + password)
    // console.log("Username: " + username)

   // console.log("Return: " + JSON.stringify(this.http.post(this.SERVER_URL, {username: username, passwort: password})))
    return this.http.post(this.SERVER_URL, {username: username, passwort: password});
  }

  isLoggedIn(): boolean {
    const n =  new Date().getTime() / 1000;
    // console.log('new Date().getTime()' + new Date().getTime())
    // console.log('Time: ' + n)
    const exp = Number(sessionStorage.getItem('expires_at'));
    // console.log('expires_at: ' + exp)
    return n < exp;
  }

  logout(): void {
    sessionStorage.removeItem('id_token');
    sessionStorage.removeItem('expires_at');
  }

}
