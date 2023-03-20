import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DatePipe} from "@angular/common";
import {FormBuilder, Validators} from "@angular/forms";
import {HttpService} from "../../services/http.service";
import {HttpClient} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {Ppk} from "../../model/Ppk";
import {GaesteListComponent} from "../../side-components/gaeste-list/gaeste-list.component";
import {PPKSummaryComponent} from "../../side-components/ppk-summary/ppk-summary.component";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-ppk-update',
  templateUrl: './ppk-update.component.html',
  styleUrls: ['./ppk-update.component.scss']
})
export class PpkUpdateComponent implements OnInit {
  constructor(private router: Router,  private snackBar: MatSnackBar, public datePipe: DatePipe, private fb: FormBuilder, public service: HttpService, private http: HttpClient,
              public dialog: MatDialog, public dataservice: DataService) {
  }
  addressForm = this.fb.group({
    datum: [null],
  });

  projects: any;
  data: any;
  auswahl = new Array()
  disable: boolean = true;
  ppk_date: any
  todayDate=this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  projektAnzahl: any;
  selectedValue: any;

  ppk_id:any

  ppk:any
  gaeste: any

  ngOnInit() {

    this.ppk_id = this.dataservice.getppk_id()
    console.log(this.ppk_id)

    this.ppk = this.service.getPPKperID(this.ppk_id).subscribe({
      next: value => {
        console.log(value)
        this.ppk = value
      }, error: err => {
        console.log("Fehler")
      }
    });

    this.ppk_date = this.ppk.datum

    this.gaeste = this.service.getPPKGaeste(this.ppk_id).subscribe({
      next: value => {
        console.log(value)
        this.gaeste = value
      }, error: err => {
        console.log("Fehler")
      }
    });

    this.projects = this.service.getProjects().subscribe({
      next: value => {
        console.log(value)
        this.projects = value
      }, error: err => {
        console.log("Fehler")
      }
    });
    this.projektAnzahl = this.service.getAnzahlProjekt().subscribe({
      next: (value: any) => {
        console.log(value)
        this.projektAnzahl = value
      }, error: (err: { message: any; }) => {}
    });
  }

  onSubmit(data: any) {
    this.ppk = {
      datum: data.datum
    }
    this.http.put<Ppk>('http://localhost:8080/ppk/update', this.ppk)
        .subscribe({
          next: (value: any) => {
            // console.log(value)
            this.ppk_id = value.ppk_id
            this.ppk_date = value.datum
            //  console.log(this.ppk_id, "ppk")
            this.setProjects();
            this.snackBar.open(`PPK wurde hinzugefügt`, undefined, {
              duration: 3000,
              panelClass: 'snackbar-dark'
            });
          }, error: (err: { message: any; }) => {
            this.snackBar.open(`Daten konnten nicht geladen werden ${err.message}`, undefined, {
              duration: 3000,
              panelClass: 'snackbar-dark'
            });
          }
        });
    this.getCheckboxValue()
    this.disable = false
  }

  setProjects() {
    for (var i = 0; i < this.auswahl.length; i++) {
      // console.log(this.auswahl[i])

      var ppkProjekte = {
        ppk_projekte_id: {
          ppk_id: {
            ppk_id: this.ppk_id
          },
          projekte_id: {
            projekt_id: Number(this.auswahl[i])
          }
        }
      }

      //POST der PPK-Projekte
      this.http.post('http://localhost:8080/ppk_projekte/add', ppkProjekte)
          .subscribe({
            next: value => {
              //  console.log(value)
            }, error: err => {
              this.snackBar.open(`Daten konnten nicht gespeichert werden ${err.message}`, undefined, {
                duration: 3000,
                panelClass: 'snackbar-dark'
              });}
          });
    }
  }
  getCheckboxValue() {
    this.auswahl = [];

    for (let i = 0; i <= this.projektAnzahl; i++) {
      const checkbox = document.getElementById("checkbox" + i,) as HTMLInputElement | null;
      if (checkbox?.checked) {
        // console.log('Checkbox is checked');
        this.auswahl.push(checkbox.value);
        // console.log(checkbox.value)
      } else {
        // console.log('Checkbox is NOT checked');
      }
      // console.log(checkbox?.checked);
    }
    // console.log(this.auswahl)
  }

  openDialog() {

      const dialogRef = this.dialog.open(GaesteListComponent, {
        width: '500px',
        data: this.ppk_id,
      });
      dialogRef.afterClosed().subscribe(result => {
        // console.log('The dialog was closed');
      });

  }

  openSummary() {
    const dialogRef = this.dialog.open(PPKSummaryComponent, {
      width: '500px',
      data: this.ppk_date,
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
    });
  }
  toDisableGast() {
    this.disable = false
  }

  onSelectedPPK(selectedValue: any) {
    
  }
}
