import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {DataService} from "../../services/data.service";
import {MatDialog} from "@angular/material/dialog";
import {BeschlussFolieFormComponent} from "../beschluss-folie-form/beschluss-folie-form.component";
import {FreieFolieFormComponent} from "../freie-folie-form/freie-folie-form.component";
import {SoftwareRequestsFormComponent} from "../software-requests-form/software-requests-form.component";
import {ProjectDetailComponent} from "../../side-components/project-detail/project-detail.component";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-ppkpresentation-form',
  templateUrl: './ppkpresentation-form.component.html',
  styleUrls: ['./ppkpresentation-form.component.scss']
})
export class PPKPresentationFormComponent implements OnInit {

  folienTypen: any[] = [
    { id: 1, name: 'Projekt-Übersicht' },
    { id: 3, name: 'Personalaufwand' },
    { id: 4, name: 'Meilenstein Übersicht' }
  ];
  private auswahl: any
  employees: any;
  roles: any;
  projects: any;
  selected: any;
  form: any;
  addressForm: any;
  projekt: any
  disbale5= false;
  disbale6= false;
  disbale2= false;
  x: any
  beschlussData:any = null;
  freiData:any = null;
  requestData:any = null;

  // Dass die Buttons disabled werden nachdem alles aufgefüllt ist, muss man ein objekt mit allen attributen der formulare anlegen und nach dem schließen
  // des formulars überprüfen ob das objekt vollständig ist

  constructor(private _router: Router,
              private snackBar: MatSnackBar,
              public dialog: MatDialog,
              public dataService: DataService,
              public service:HttpService,
              private _formBuilder: FormBuilder) {}

  ngOnInit() {
    if(!this.dataService.isloggedIn){
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
    this.projects = this.service.getProjects().subscribe({
      next: value => {
        // console.log(value)
        this.projects = value
      }, error: err => {}
      });
  }
  getCheckboxValue(){
    this.auswahl = [];

    for (let i = 0; i <= 6; i++) {
      const checkbox = document.getElementById(
        "checkbox"+ i,
      ) as HTMLInputElement | null;

      if (checkbox?.checked) {
        // console.log('Checkbox is checked');
        this.auswahl.push(checkbox.value);
        // console.log(checkbox.value)

      } else {
        //  console.log('Checkbox is NOT checked');
      }
      // console.log(checkbox?.checked);
    }
    // console.log(this.auswahl)
  }
  onSubmit(data: any) {
    this.getProjektInfo();
  }
  getProjektInfo(){
    this.projekt = this.service.getProjectByIdNr(this.selected).subscribe({
      next: value => {
        // console.log(value)
        this.projekt = value
      }, error: err => {}});
  }

  setProjektID() {
    // console.log(this.selected + "Projekt")
    this.dataService.projekt_id(this.selected)
    this.disbale5 = false;
    this.disbale6 = false;
    this.disbale2 = false;
  }

  openEntscheidung() {
    const dialogRef = this.dialog.open(BeschlussFolieFormComponent, {
      width: '100%',
      height: '75%',
      data: [this.selected,this.beschlussData],
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this.x = document.getElementById("button5");
      if(result.data.titel != null && result.data.freitext != null){
        this.x.innerText = "Entscheindungs-Folie ✓"
        this.disbale5 = true;
        this.beschlussData = result.data;
      }
    });
  }
  openFreieFolie() {
    const dialogRef = this.dialog.open(FreieFolieFormComponent, {
      width: '100%',
      height: '75%',
      data: [this.selected,this.freiData],
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this.x = document.getElementById("button6");
      if(result.data.freitext != null && result.data.beschreibung != null){
        this.x.innerText = "Freie-Folie ✓"
        this.disbale6 = true;
        this.freiData = result.data;
      }
    });
  }
  openSR() {
    const dialogRef = this.dialog.open(SoftwareRequestsFormComponent, {
      width: '100%',
      height: '75%',
      data: [this.selected,this.requestData],
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this.x = document.getElementById("button2");
      if(result.data.status != null && result.data.beschreibung != null){
        this.x.innerText = "Software-Anforderungen ✓"
        this.disbale2 = true;
        this.requestData = result.data;
      }
    });
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(ProjectDetailComponent, {
      width: '70%',
      data: this.dataService.projekt_id
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
    });
  }
  postData(){
    this.service.http.post('http://localhost:8080/freiefolien/add', this.freiData)
        .subscribe({
          next: value => {
            console.log("hat funktioniert")
          }, error: err => {
            console.log(this.freiData)
            this.snackBar.open(`Projekt hinzufügen ist fehlgeschlagen: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
          }
        });


  this.service.http.post('http://localhost:8080/beschlussfolien/add', this.beschlussData)
      .subscribe({
        next: value => {
          // console.log(value)
          console.log("Beschlussfolie hinzugefügt!")
        }, error: err => {
          this.snackBar.open(`Projekt hinzufügen ist fehlgeschlagen: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
          console.log(this.beschlussData)
        }
      });



  this.service.http.post( "http://localhost:8080/softwareanforderungen/add/" + this.requestData.auswahl[1] + "/" + this.requestData.auswahl[2] + "/" + this.requestData.auswahl[3] + "/" + this.requestData.auswahl[4] + "/" +
      this.requestData.auswahl[5] + "/" + this.requestData.auswahl[6] + "/" +  this.requestData.auswahl[7], this.requestData)
      .subscribe({
        next: value => {
          // console.log(value)
          console.log("Softwareanfoderungsfolie hinzugefügt!")
        }, error: err => {
          this.snackBar.open(`Projekt hinzufügen ist fehlgeschlagen: ${err.message}`, undefined, {duration: 3000, panelClass: 'snackbar-dark'});
          console.log(this.requestData)
        }

      });
  }
}
