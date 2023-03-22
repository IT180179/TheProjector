import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {HttpService} from "../../services/http.service";
import {Person} from "../../model/Person";
import {DataService} from "../../services/data.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import * as moment from "moment";

@Component({
  selector: 'app-resource-form',
  templateUrl: './resource-form.component.html',
  styleUrls: ['./resource-form.component.scss']
})
export class ResourceFormComponent implements OnInit {
  form: FormGroup;
  employeesOfProject: any;
  roles: any;
  employees: any;
  projects: any;
  selectedValue: any;
  einsaetze: any;
  newdata: any;
  showtable: boolean = false;
  user_id: any
  myDate: any
  einsatz: any

  constructor(private _router: Router, private snackBar: MatSnackBar, private http: HttpClient, private fb: FormBuilder, public service: HttpService, public dataservice: DataService) {
    this.form = this.fb.group({
      projekt_id: [null, Validators.required],
      rollen_id: [null, Validators.required],
      arbeitsstunden: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    let d = new Date()
    this.myDate = moment(d).format('YYYY-MM-DD')

    if(!this.dataservice.isloggedIn){
      this._router.navigate(['**']);
    }
    this.user_id = this.dataservice.user_id;

    this.employees = this.service.getEmployees().subscribe({
      next: value => {
        //  console.log(value)
        this.employees = value
      }, error: err => {
        this.snackBar.open(`Daten konnten nicht geladen werden: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
      }
    });


    this.projects = this.service.getProjectsByPerson(this.user_id).subscribe({
      next: value => {
        // console.log(value)
        this.projects = value
      }, error: err => {
        this.snackBar.open(`Daten konnten nicht geladen werden: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
      }
    });

    this.einsaetze = this.service.getEinsatzPerPerson(this.user_id).subscribe({
      next: value => {
        // console.log(value)
        this.einsaetze = value
      }, error: err => {
        this.snackBar.open(`Daten konnten nicht geladen werden: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
      }
    });
  }
  onSubmit(data: any) {

    this.newdata = {
      einsaetze_id:{
        einsaetze_id:{
          personen_id:{personen_id: this.user_id},
          projekte_id: {projekt_id: data.projekt_id},
          rollen_id:{rollen_id:  Number(data.rollen_id)}
        }
      },
      arbeitszeit: Number(data.arbeitsstunden),
      date: this.myDate
    };

    console.log(this.newdata)
    this.http.post<any>('http://localhost:8080/arbeitszeiten/add', this.newdata)
      .subscribe({
        next: value => {
          this.snackBar.open(`Daten wurden gespeichert!`, undefined, {
            duration: 3000,
            panelClass: 'snackbar-dark'
          });
          this._router.navigate(['resourcen_overview']);
        }, error: err => {
          this.snackBar.open(`Daten konnten nicht gespeichert werden ${err.message}`, undefined, {
            duration: 3000,
            panelClass: 'snackbar-dark'
          });
        }
      });
  }
  onSelectedProject(id: number) {
    this.employeesOfProject = this.service.getPersonsOfProjectsNumber(id).subscribe({
      next: value => {
        //  console.log(value)
        this.employeesOfProject = value
      }, error: err => {
        this.snackBar.open(`Daten konnten nicht geladen werden: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
      }
    });
    this.showtable = true
  }

  allowedEinsaetze = []
  changeEinsaetze() {
    this.allowedEinsaetze = []
    for (let i of this.einsaetze) {

      console.log("hi")
      console.log(i.einsaetze_id.projekte_id.projekt_id)
      console.log(this.selectedValue)
      if(i.einsaetze_id.projekte_id.projekt_id == this.selectedValue){
        console.log("hi")
        // @ts-ignore
        this.allowedEinsaetze.push(i)
        console.log(this.allowedEinsaetze)
      }
    }
  }
}
