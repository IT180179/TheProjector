import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../projects-overview/projects-overview.component";
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-gaeste-list',
  templateUrl: './gaeste-list.component.html',
  styleUrls: ['./gaeste-list.component.scss']
})
export class GaesteListComponent implements OnInit {
  disable: any;
  gaeste_list: any;
  ppk_id:any

  addressForm = this.fb.group({
    gast: [null, Validators.required]
  });

  constructor(private _router: Router, private dataService: DataService, private fb: FormBuilder, public service: HttpService, private http: HttpClient, public dialogRef: MatDialogRef<GaesteListComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData, private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }

    this.ppk_id = this.dataService.getppk_id()
    console.log(this.ppk_id)
    this.gaeste_list = this.service.getPPKGaeste(this.ppk_id).subscribe({
      next: value => {
        // console.log(value)
        this.gaeste_list = value
      }, error: err => {}
    });
  }

  newGast(value: string) {
    this.ngOnInit();
    if (value != null) {
      var gast = {
        ppk_id: {
          ppk_id: this.data
        },
        name: value
      }
      this.http.post('http://localhost:8080/gaeste/add', gast)
        .subscribe({
          next: value => {
            // console.log(value)
            this.snackBar.open(`Gast wurde gespeichert!`, undefined, {
              duration: 3000,
              panelClass: 'snackbar-dark'
            });
          }, error: err => {
            this.snackBar.open(`Daten konnten nicht hinzugefügt werden ${err.message}`, undefined, {
              duration: 3000,
              panelClass: 'snackbar-dark'
            });
          }
        });
    }
    this.ngOnInit();
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
