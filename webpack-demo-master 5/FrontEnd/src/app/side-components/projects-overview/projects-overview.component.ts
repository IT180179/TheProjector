import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {ProjectDetailComponent} from "../project-detail/project-detail.component";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";
import {LoginComponent} from "../../login/login.component";

export interface DialogData {
  id: string;
}

@Component({
  selector: 'app-projects-overview',
  templateUrl: './projects-overview.component.html',
  styleUrls: ['./projects-overview.component.scss']
})
export class ProjectsOverviewComponent implements OnInit {

  text = '';
  id: number | undefined;
  projects: any;
  foundprojects = [];
  recht:any
  isLoggedIn: any

  constructor(public loginComponent: LoginComponent, public dataService: DataService,
              public _router: Router,public service: HttpService, public dialog: MatDialog,
              private snackBar: MatSnackBar, public data: DataService) {
  }

  openDialog(projekt_id: number): void {
    this.id = projekt_id;
    const dialogRef = this.dialog.open(ProjectDetailComponent, {
      width: '70%',
      data: projekt_id,
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
      this.id = result;
    });
  }
  ngOnInit(): void {
    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }

    this.recht = this.data.recht;
    this.isLoggedIn = this.data.isloggedIn;

    this.projects = this.service.getProjects().subscribe({
      next: value => {
        //  console.log(value)
        this.projects = value;
        // @ts-ignore
        this.foundprojects = value
          console.log(this.foundprojects)
      }, error: err => {
        //  console.log("Es können keine Projekte abgefragt werden")
        this.snackBar.open(`Projekt laden ist fehlgeschlagen: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
      }
    });
  }

  doSearch() {
    if(this.text == ""){
      this.foundprojects = this.projects;
    }else {
   this.service.search(this.text).subscribe( {
      next: value => {
        // @ts-ignore
        this.foundprojects = value;
      },error: err => {
       this.snackBar.open(`Projekt laden ist fehlgeschlagen: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
      }
    })
  }
    console.log(this.foundprojects.length)
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
}
