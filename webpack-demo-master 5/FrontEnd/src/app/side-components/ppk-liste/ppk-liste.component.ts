import { Component, OnInit } from '@angular/core';
import {LoginComponent} from "../../login/login.component";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";
import {HttpService} from "../../services/http.service";
import {MatDialog} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ProjectDetailComponent} from "../project-detail/project-detail.component";

@Component({
  selector: 'app-ppk-liste',
  templateUrl: './ppk-liste.component.html',
  styleUrls: ['./ppk-liste.component.scss']
})
export class PpkListeComponent implements OnInit {
  text = '';
  id: number | undefined;
  ppks: any;
  foundprojects = [];
  recht:any
  isLoggedIn: any

  constructor(public loginComponent: LoginComponent,
              public _router: Router,public service: HttpService, public dialog: MatDialog,
              private snackBar: MatSnackBar, public data: DataService) {
  }

  openDialog(projekt_id: number): void {
    this.id = projekt_id;
    const dialogRef = this.dialog.open(ProjectDetailComponent, {
      width: '70%',
      data: projekt_id,
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this.id = result;
    });
  }
  ngOnInit(): void {
    if(!this.data.isloggedIn){
      this._router.navigate(['**']);
    }

    this.recht = this.data.recht;
    this.isLoggedIn = this.data.isloggedIn;

    this.ppks = this.service.getPPK().subscribe({
      next: value => {
        //  console.log(value)
        this.ppks = value
      }, error: err => {
        //  console.log("Es können keine Projekte abgefragt werden")
      }
    });
  }


  setPPK(ppk_id: any) {
    this.data.ppk_id = ppk_id
  }
}
