import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Projekte} from "../../model/Projekte";
import {DatePipe} from "@angular/common";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {HttpService} from "../../services/http.service";
import {AuthService} from "../../services/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-create-project-form',
  templateUrl: './create-project-form.component.html',
  styleUrls: ['./create-project-form.component.scss']
})
export class CreateProjectFormComponent implements OnInit {

  addressForm = this.fb.group({
    titel: [null, Validators.required],
    kategorie_id: [null, Validators.required],
    inhalt: [null, Validators.compose([Validators.maxLength(860), Validators.required])],
    ziel: [null, Validators.required],
    budget: [null ,Validators.compose([Validators.maxLength(221), Validators.required])],
    start_datum:[null, Validators.required],
    end_datum:  [null, Validators.required]
  });

  constructor( private _router: Router, public dialog: MatDialog, private fb: FormBuilder,
               public datePipe: DatePipe, private authService: AuthService, private snackBar: MatSnackBar,
               private http: HttpClient, public service: HttpService, public dataService: DataService) { }

  categorie: any;
  todayDate=this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  data: any;
  newdata: Projekte | undefined;
  projects: any;

  ngOnInit():void {

    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }
    this.categorie = this.service.getCategorie().subscribe({
      next: value => {
        // console.log(value)
        this.categorie = value
      }, error: err => {}});
  }

  onSubmit(data: Projekte) {
      this.newdata = {
        titel: data.titel,
        inhalt: data.inhalt,
        ziel: data.ziel,
        status: 1,
        budget: data.budget,
        start_datum: data.start_datum,
        end_datum: data.end_datum,
        kategorie_id: {
          kategorien_id: Number(data.kategorie_id)
        }
      };
      this.http.post('http://localhost:8080/projekte/add', this.newdata)
        .subscribe({
          next: value => {
            // console.log(value)
          }, error: err => {
            this.snackBar.open(`Projekt hinzufügen ist fehlgeschlagen: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
          }
        });
      this.openSummary()
    }

  openSummary() {
    const dialogRef = this.dialog.open(SummaryComponent, {
      width: '400px',
      height: '250px',
      data: this.newdata?.titel,
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this._router.navigate(['/project_overview']);
    });
  }
}
