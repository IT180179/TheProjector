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
        }, error: err => {}
      });
  }
}
