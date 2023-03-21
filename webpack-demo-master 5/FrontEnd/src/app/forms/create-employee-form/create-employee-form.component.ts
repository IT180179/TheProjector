import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {FormBuilder, Validators} from "@angular/forms";
import {Person} from "../../model/Person";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-create-employee-form',
  templateUrl: './create-employee-form.component.html',
  styleUrls: ['./create-employee-form.component.scss']
})
export class CreateEmployeeFormComponent implements OnInit {

  constructor(private _router: Router, public dialog: MatDialog, private http: HttpClient,
              public service: HttpService, private fb: FormBuilder, private snackBar: MatSnackBar) {
  }

  data: any;
  newdata: any;
  employees: any;
  abteilung: any;
  bereich: any;

  addressForm = this.fb.group({
    vorname: [null, Validators.required],
    nachname: [null, Validators.required],
    passwort: [null, Validators.compose([Validators.minLength(4), Validators.required])],
    abteilungs_id: [null, Validators.required],
    bereich_id: [null, Validators.required]
  });

  ngOnInit(): void {

    this.employees = this.service.getEmployees().subscribe({
      next: value => {
        // console.log(value)
        this.employees = value
      }, error: err => {}
    });
    this.abteilung = this.service.getAbteilung().subscribe({
      next: value => {
        console.log(value)
        this.abteilung = value
      }, error: err => {}
    });
    this.bereich = this.service.getBereiche().subscribe({
      next: value => {
        console.log(value)
        this.bereich = value
      }, error: err => {}
    });
    if(!this.data.isloggedIn){
      this._router.navigate(['**']);
    }
  }

  onSubmit(data: any) {
    //JSON für POST
    this.newdata = {
      vorname: data.vorname,
      nachname: data.nachname,
      rechte: 2,
      passwort: data.passwort,
      abteilungs_id:{
        abteilungs_id: data.abteilungs_id,
        bereichs_id:{
          bereich_id: data.bereich_id
        }
      }
    };

    //POST-Person
    this.http.post<Person>('http://localhost:8080/personen/add', this.newdata)
      .subscribe({
        next: value => {
          // console.log(value)
        }, error: err => {
          this.snackBar.open(`Daten konnten nicht gespeichert werden ${err.message}`, undefined, {
            duration: 3000,
            panelClass: 'snackbar-dark'
          });
        }
      });
    this.openSummary();
  }

  openSummary() {
    const dialogRef = this.dialog.open(SummaryComponent, {
      width: '400px',
      height: '250px'
    });
    dialogRef.afterClosed().subscribe(result => {
      this._router.navigate(['/add-employee']);
    });
  }
}
