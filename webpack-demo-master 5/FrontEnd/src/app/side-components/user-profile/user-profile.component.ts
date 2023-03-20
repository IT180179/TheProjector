import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  constructor(private router: Router, private auth: AuthService,
              private snackBar: MatSnackBar, public http: HttpClient, private service: HttpService, public dataService: DataService) {
  }
  employees: any;
  fachkoordinator: any;
  project: any;
  projectmanager: any;
  user_id: any;

  ngOnInit(): void {

    if(!this.dataService.isloggedIn){
      this.router.navigate(['**']);
    }
    this.user_id = this.dataService.user_id;
    console.log(this.user_id)

    this.employees = this.service.getEmployeeById(this.user_id).subscribe({
      next: value => {
        // console.log(value)
        this.employees = value
      }, error: err => {}
    });

    this.fachkoordinator = this.service.countFachkoordinator(this.user_id).subscribe({
      next: value => {
        // console.log(value)
        this.fachkoordinator = value
      }, error: err => {}
    });

    this.project = this.service.countProjects(this.user_id).subscribe({
      next: value => {
        //  console.log(value)
        this.project = value
      }, error: err => {}
    });
    this.projectmanager = this.service.countProjectmanager(this.user_id).subscribe({
      next: value => {
        // console.log(value)
        this.projectmanager = value
      }, error: err => {}
    });
  }

  lougout() {
    this.auth.logout()
    this.router.navigate(['login']);
  }
}
