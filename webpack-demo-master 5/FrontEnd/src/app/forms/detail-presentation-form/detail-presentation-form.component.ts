import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {HttpService} from "../../services/http.service";
import {DatePipe} from "@angular/common";
import {AuthService} from "../../services/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-detail-presentation-form',
  templateUrl: './detail-presentation-form.component.html',
  styleUrls: ['./detail-presentation-form.component.scss']
})
export class DetailPresentationFormComponent implements OnInit {

  addressForm = this.fb.group({
    project: [null, Validators.required]
  });

  constructor(private data: DataService, private router: Router, public datePipe: DatePipe, private authService: AuthService,
              private snackBar: MatSnackBar, private _router: Router, public dialog: MatDialog, private fb: FormBuilder, public service: HttpService) {
  }

  employees: any;
  roles: any;
  projects: any;
  selected = '1';

  ngOnInit(): void {
    if(!this.data.isloggedIn){
      this.router.navigate(['**']);
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

  onSubmit() {
    this.openSummary()
  }
  openSummary() {
    const dialogRef = this.dialog.open(SummaryComponent, {
      width: '400px',
      height: '250px'
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this._router.navigate(['/project_overview']);
    });
  }

  getDetailPresentation(){
    console.log("Detail-Präsentation wird generiert")

      this.service.getDetailPowerpoint().subscribe({
        next: blob => {
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = 'Detail-Präsentation.pptx';
        link.click();
      }, error: err => {
      this.snackBar.open(`Präsentation herunterladen ist fehlgeschlagen: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
    }});

  }

  displayStatus(status:number):string{
    if(status==1){
      return "🟢 planmäßig"
    }else if(status==2){
      return "🟠 im Verzug";
    }else if(status==3){
      return "🔴 verspätet";
    }else{
      return "invalid status";
    }
  }

  setProjektId(id: any) {
    this.data.projekt_id = id
  }
}
