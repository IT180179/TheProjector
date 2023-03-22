import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Ppk} from "../../model/Ppk";
import {GaesteListComponent} from "../../side-components/gaeste-list/gaeste-list.component";
import {MatDialog} from "@angular/material/dialog";
import {PPKSummaryComponent} from "../../side-components/ppk-summary/ppk-summary.component";
import {DatePipe} from "@angular/common";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-ppk-form',
  templateUrl: './create-ppkform.component.html',
  styleUrls: ['./create-ppkform.component.scss']
})
export class CreatePPKFormComponent implements OnInit {
  constructor(private router: Router,  private snackBar: MatSnackBar, public datePipe: DatePipe, private fb: FormBuilder, public service: HttpService, private http: HttpClient,
               public dialog: MatDialog) {
  }
  addressForm = this.fb.group({
    datum: [null, Validators.required],
  });

  projects: any;
  data: any;
  ppk: { datum: any; } | undefined
  ppk_id: any
  auswahl = new Array()
  disable: boolean = true;
  ppk_date: any
  todayDate=this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  projektAnzahl: any;

  ngOnInit() {

    this.projects = this.service.getProjects().subscribe({
        next: value => {
         // console.log(value)
          this.projects = value
        }, error: err => {
        this.snackBar.open(`Daten konnten nicht gespeichert werden ${err.message}`, undefined, {
          duration: 3000,
          panelClass: 'snackbar-dark'
        });
      }
    });
    this.projektAnzahl = this.service.getAnzahlProjekt().subscribe({
      next: (value: any) => {
        // console.log(value)
        this.projektAnzahl = value
      }, error: (err: { message: any; }) => {
        this.snackBar.open(`Daten konnten nicht gespeichert werden ${err.message}`, undefined, {
          duration: 3000,
          panelClass: 'snackbar-dark'
        });
      }
    });
  }

  onPost(data: any) {
    this.ppk = {
      datum: data.datum
    }
    this.http.post<Ppk>('http://localhost:8080/ppk/add', this.ppk)
      .subscribe({
        next: (value: any) => {
         // console.log(value)
          this.ppk_id = value.ppk_id
          this.ppk_date = value.datum
         //  console.log(this.ppk_id, "ppk")
          console.log(value)

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
    this.setProjects();
    this.getCheckboxValue()
    this.openSummary();
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
            });
          }
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
    console.log("gast")
    console.log(this.ppk_id + " " + this.ppk_date)
    try {
    if (this.ppk_id != null && this.ppk_date != null) {
      const dialogRef = this.dialog.open(GaesteListComponent, {
        width: '500px',
        data: this.ppk_id,
      });
      dialogRef.afterClosed().subscribe(result => {
        // console.log('The dialog was closed');
      });
    }
    } catch (error) {

    }
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
}


