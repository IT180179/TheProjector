import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-ppk-information',
  templateUrl: './ppk-information.component.html',
  styleUrls: ['./ppk-information.component.scss']
})
export class PpkInformationComponent implements OnInit {

  constructor(private router: Router, private snackBar: MatSnackBar, public dataservice: DataService, public service: HttpService, public http: HttpClient) { }

  nextPpk: any;
  ppkInfos: any

  ngOnInit(): void {
    if(!this.dataservice.isloggedIn){
      this.router.navigate(['**']);
    }
    this.nextPpk = this.service.getNextPPK().subscribe({
      next: value => {
         console.log(value)
        this.nextPpk = value
      }, error: err => {
        this.snackBar.open(`Nächstes PPK laden ist fehlgeschlagen: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
      }
    })

    this.ppkInfos = this.http.get('http://localhost:8080/ppk/getNextPPKWithProjektAndGaeste').subscribe(
      {
        next: value => {
          console.log(value)
          this.ppkInfos = value
        }, error: err => {
          this.snackBar.open(`PPK-Infos laden ist fehlgeschlagen: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
        }}
    )

  }

}
