import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {DataService} from "../../services/data.service";
import {HttpClient} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {ChangeProjekte} from "../../model/ChangeProjekte";
import {DatePipe} from "@angular/common";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {Router} from "@angular/router";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-change-project-form',
  templateUrl: './change-project-form.component.html',
  styleUrls: ['./change-project-form.component.scss']
})
export class ChangeProjectFormComponent implements OnInit {

  employees: any;
  roles: any;
  projects: any;
  selected: any;
  id: any;
  project: any;
  todayDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  data: any;
  newdata: any;

  addressForm = this.fb.group({
    titel: [null],
    status: [null],
    inhalt: [null],
    ziel: [null],
    budget: [null],
    end_datum: [null]
  });

  constructor(private router: Router, private snackBar: MatSnackBar, private _router: Router, public dialog: MatDialog, public datePipe: DatePipe, private fb: FormBuilder,
              public service: HttpService, public dataService: DataService, public http: HttpClient) {
  }

  ngOnInit(): void {
    if(!this.dataService.isloggedIn){
      this.router.navigate(['**']);
    }
    this.id = this.dataService.projekt_id;

    this.employees = this.service.getEmployees().subscribe({
      next: value => {
        // console.log(value)
        this.employees = value
      }, error: err => {}
    });
    this.roles = this.service.getRoles().subscribe({
      next: value => {
        // console.log(value)
        this.roles = value
      }, error: err => {}
    });
    this.projects = this.service.getProjects().subscribe({
      next: value => {
        // console.log(value)
        this.projects = value
      }, error: err => {}
    });
    this.project = this.service.getProjectByIdNr(this.id).subscribe({
      next: value => {
       // console.log(value)
        this.project = value
      }, error: err => {}
    });
  }

  onSubmit(data: ChangeProjekte) {
    //JSON für UPDATE
    this.newdata = {
      projekt_id: this.project.projekt_id,
      titel: data.titel || this.project.titel ,
      status: Number(data.status) || this.project.status,
      inhalt: data.inhalt || this.project.inhalt,
      ziel: data.ziel || this.project.ziel,
      budget: Number(data.budget) || this.project.budget,
      start_datum: this.project.start_datum,
      end_datum: data.end_datum || this.project.end_datum,
      kategorie_id: {
        kategorien_id: this.project.kategorie_id.kategorien_id
      }
    }
    //UPDATE DES PROJEKTS
    this.http.put('http://localhost:8080/projekte/update', this.newdata)
      .subscribe({
        next: value => {
          console.log(value)
        }, error: err => {
          this.snackBar.open(`Daten konnten nicht gespeichert werden ${err.message}`, undefined, {
            duration: 3000,
            panelClass: 'snackbar-dark'
          });
        }
      });
    this.openSummary()
  }
  openSummary() {
    const dialogRef = this.dialog.open(SummaryComponent, {
      width: '400px',
      height: '250px',
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this._router.navigate(['/project_overview']);
    });
  }
}
