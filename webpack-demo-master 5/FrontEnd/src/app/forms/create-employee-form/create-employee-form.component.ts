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

  addressForm = this.fb.group({
    vorname: [null, Validators.required],
    nachname: [null, Validators.required],
    passwort: [null, Validators.compose([Validators.minLength(4), Validators.required])]
  });

  data: any;
  newdata: any;
  employees: any;
  roles: any;

  ngOnInit(): void {
    if(!this.data.isloggedIn){
      this._router.navigate(['**']);
    }
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
  }

  onSubmit(data: Person) {
    //JSON für POST
    this.newdata = {
      vorname: data.vorname,
      nachname: data.nachname,
      rechte: 2,
      passwort: data.passwort
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
      // console.log('The dialog was closed');
      this._router.navigate(['/add-employee']);
    });
  }
}
