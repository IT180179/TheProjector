import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Person} from "../../model/Person";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthService} from "../../services/auth.service";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-employee-form',
  templateUrl: './add-employee-form.component.html',
  styleUrls: ['./add-employee-form.component.scss']
})
export class AddEmployeeFormComponent implements OnInit {

  form: FormGroup;
  employeesOfProject: any;
  roles: any;
  employees: any;
  projects: any;
  selectedValue: any;
  einsaetze: any;
  newdata: any;
  showtable: boolean = false;

  constructor(private router: Router, private data: DataService, private authService: AuthService, private snackBar: MatSnackBar, private snackbar: MatSnackBar, private http: HttpClient, private fb: FormBuilder, public service: HttpService) {
    this.form = this.fb.group({
      projekt_id: [null, Validators.required],
      personen_id: [null, Validators.required],
      rollen_id: [null, Validators.required],
      arbeitsstunden: [null, [Validators.required, Validators.min(0.01), Validators.max(1)]]
    });
  }
  ngOnInit(): void {
    // Überprüfen ob User eingeloggt ist
    if (!this.data.isloggedIn) {
      this.router.navigate(['**']);
    }
    //GET-Mitarbeiter
    this.employees = this.service.getEmployees().subscribe({
      next: value => {
        this.employees = value
      }, error: err => {
      }
    });
    //GET-Rollen
    this.roles = this.service.getRoles().subscribe({
      next: value => {
        this.roles = value
      }, error: err => {
      }
    });
    //GET-Projects
    this.projects = this.service.getProjects().subscribe({
      next: value => {
        this.projects = value
      }, error: err => {
      }
    });
    //GET-Einsatz
    this.einsaetze = this.service.getEinsaetze().subscribe({
      next: value => {
        this.einsaetze = value
      }, error: err => {
      }
    });
  }

  onSubmit(data: any) {
    //JSON-Objekt für POST
    this.newdata = {
      einsaetze_id: {
        personen_id: {personen_id: Number(data.personen_id)},
        projekte_id: {projekt_id: data.projekt_id},
        rollen_id: {rollen_id: Number(data.rollen_id)}
      },
      arbeitsstunden: Number(data.arbeitsstunden)
    };
    //POST des neuen Mitarbeiters
    this.http.post<Person>('http://localhost:8080/einsaetze/add', this.newdata)
      .subscribe({
        next: () => {
          this.snackbar.open("Person wurde erfolgreich hinzugefügt", undefined, {duration: 3000});
        },
        error: err => {
          this.snackbar.open(`Hinzufügen fehlgeschlagen: ${err.message}`, undefined, {duration: 3000});
        }
      });
    //Komponente neu laden
    this.router.navigateByUrl('/add-employee', {skipLocationChange: true}).then(() => {
      this.router.navigate(['add-employee']);
    });
  }

  onSelectedProject(id: number) {
    this.employeesOfProject = this.service.getPersonsOfProjectsNumber(id).subscribe({
      next: value => {
        this.employeesOfProject = value
      }, error: err => {
      }
    });
    this.showtable = true
  }
}
