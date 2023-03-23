import {Component, OnInit} from '@angular/core';
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-info-ppk',
  templateUrl: './info-ppk.component.html',
  styleUrls: ['./info-ppk.component.scss']
})
export class InfoPpkComponent implements OnInit {

  constructor(private route: ActivatedRoute, public _router: Router, public service: HttpService, public dataservice: DataService, private snackBar: MatSnackBar,) {
  }
  ppk: any;

  ngOnInit(): void {
      this.ppk = this.service.getNextPPK().subscribe({
        next: value => {
          this.ppk = value
          //console.log(this.ppk)
        }, error: err => {
        }
      });

  }
}
