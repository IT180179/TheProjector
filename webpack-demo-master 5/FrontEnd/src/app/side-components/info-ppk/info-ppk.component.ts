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
    //this.route.params.subscribe(params =>{
      this.ppk = this.service.getPPK().subscribe({
        next: value => {
          const firstPPK = Object.values(value)[0];
          //  console.log(firstPPK)
          //  console.log(value[0].ppk_id)
          this.ppk = value
          this.dataservice.ppk_id(value[0].ppk_id)
          console.log('PPK value: ' + value)
        }, error: err => {}
      });
    //})


  }
}
