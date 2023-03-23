import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {

  constructor(public router: Router, public service: HttpService, public dataService: DataService, private snackBar: MatSnackBar) {
  }
  employees: any;

  ngOnInit():void {
    if(!this.dataService.isloggedIn){
      this.router.navigate(['**']);
    }
    this.employees = this.service.getEmployeesAndRessources()
      .subscribe({
        next: value => {
          //   console.log(value)
          this.employees = value
        }, error: err => {
          this.snackBar.open(`Mitarbeiter laden ist fehlgeschlagen: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
        }
      });
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
