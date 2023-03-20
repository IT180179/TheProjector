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
    if (!this.dataService.isloggedIn) {
      this._router.navigate(['**']);
    }

    this.ppk_id = this.dataService.getppk_id()
    console.log(this.ppk_id)

    this.refresh()


  }
gast:any
  newGast(value: string) {
    if (value != null) {

      this.gast = {
        ppk_id: {
          ppk_id: this.data
        },
        name: value
      }
      console.log(this.gast)

      this.http.post('http://localhost:8080/gaeste/add', this.gast)
        .subscribe({
          next: value => {
            // console.log(value)
            this.snackBar.open(`Gast wurde gespeichert!`, undefined, {
              duration: 3000,
              panelClass: 'snackbar-dark'
            });
            this.refresh()
          }, error: err => {
            this.snackBar.open(`Daten konnten nicht hinzugefügt werden ${err.message}`, undefined, {
              duration: 3000,
              panelClass: 'snackbar-dark'
            });
          }
        });

    }
  }

  refresh() {

    this.gaeste_list = this.service.getPPKGaeste(this.gast.ppk_id.ppk_id).subscribe({
      next: value => {
        // console.log(value)
        this.gaeste_list = value
      }, error: err => {
      }});
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
