import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {HttpClient} from "@angular/common/http";
import {DataService} from "../../services/data.service";
import {Meilensteine} from "../../model/Meilensteine";
import {DatePipe} from "@angular/common";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {Route, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-add-milestone-form',
  templateUrl: './add-milestone-form.component.html',
  styleUrls: ['./add-milestone-form.component.scss']
})
export class AddMilestoneFormComponent implements OnInit {
  data: any;
  todayDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  newData: Meilensteine | undefined;
  projekt_id: any;

  addressForm = this.fb.group({
    titel: [null, Validators.required],
    beschreibung: [null, Validators.required],
    start_datum: [''],
    end_datum: ['', Validators.required],
    status: [null, Validators.required]
  });

  constructor(private router: Router, private authService: AuthService, private snackBar: MatSnackBar,
              private _router: Router, public dialog: MatDialog, public datePipe: DatePipe, private http: HttpClient,
              private fb: FormBuilder, public dataService: DataService) {}

  ngOnInit(): void {
    // Überprüfen ob User eingeloggt ist
    if(!this.data.isloggedIn){
      this.router.navigate(['**']);
    }
    // Projekt-ID des zur Zeit ausgewählten Projekts
    this.projekt_id = this.dataService.projekt_id
  }
  onSubmit(data: Meilensteine) {
    data.start_datum = new Date().toLocaleDateString('sv');
    data.status = Number(data.status);
    console.log(data.start_datum);
    console.log(data.status);
    //JSON-Objekt für POST
    this.newData = {
      titel: data.titel,
      beschreibung: data.beschreibung,
      status: data.status,
      start_datum: data.start_datum,
      end_datum: data.end_datum,
      projekt_id: {
        projekt_id: this.dataService.projekt_id
      }
    }
    console.log(data)
    //POST-Neues Meilensteins
    this.http.post('http://localhost:8080/meilensteine/add', this.newData)
      .subscribe({
        next: value => {
          this.snackBar.open(`Meilenstein wurde hinzugefügt`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
          // console.log(value)
        }, error: err => {
          this.snackBar.open(`Daten konnten nicht hinzugefügt werden ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
        }
      });
    this.openSummary()
  }

  openSummary() {
    const dialogRef = this.dialog.open(SummaryComponent, {
      width: '400px',
      height: '250px'
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this._router.navigate(['/milestone_list']);
    });
  }

}
