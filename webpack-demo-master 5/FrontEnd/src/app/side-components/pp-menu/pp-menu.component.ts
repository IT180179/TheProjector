import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";
import {HttpService} from "../../services/http.service";

@Component({
  selector: 'app-pp-menu',
  templateUrl: './pp-menu.component.html',
  styleUrls: ['./pp-menu.component.scss']
})
export class PPMenuComponent implements OnInit {

  constructor(public data: DataService, public router: Router,private http: HttpService) {
  }
  rechte: any;
  isLoggedIn: any;
  disableButton: boolean = true

  ngOnInit(): void {
    if(!this.data.isloggedIn){
      this.router.navigate(['**']);
    }

    this.rechte = this.data.recht;
    this.isLoggedIn = this.data.isloggedIn

    if (this.rechte == 1) {
      this.disableButton = false
    }

  }
  getPresentation(){
    this.http.getPPKPowerpoint().subscribe(blob => {
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = 'PPK-Meeting.pptx';
      link.click();
    });
  }
}
