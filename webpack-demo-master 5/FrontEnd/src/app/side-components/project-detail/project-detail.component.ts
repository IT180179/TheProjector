import {Component, Inject, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTable} from "@angular/material/table";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../projects-overview/projects-overview.component";
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.scss']
})
export class ProjectDetailComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<any>;

  constructor(public service: HttpService,
              public dialogRef: MatDialogRef<any>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData,
              public dataService: DataService, private snackBar: MatSnackBar,
              private router: Router) {
  }

  project: any;
  status: any;
  employees: any;
  projekt_id: any;
  rechte: any;
  boo: any = false

  ngOnInit(): void {

    if(!this.dataService.isloggedIn){
      this.router.navigate(['**']);
    }
    this.rechte = this.dataService.recht;
    if (this.rechte == 1) {
      this.boo = false
    }
    this.dataService.projekt_id = this.data;

    this.project = this.service.getProjectById(this.data).subscribe({
      next: value => {
        //  console.log(value)
        this.project = value
      }, error: err => {
      }
    });
    this.employees = this.service.getEmployeesAndRessourcesPerProjekt(this.data).subscribe({
      next: value => {
        //   console.log(value)
        this.employees = value
      }, error: err => {
      }
    });
  }
  onNoClick(): void {
    this.dialogRef.close();
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

  translateRoles(rechte:number):string{
    switch (rechte){
      case 1:
        return "Projektmanager";
        break;
      case 2:
        return "Mitarbeiter";
        break;
      default:
        return "Invalid"

    }
  }
}
