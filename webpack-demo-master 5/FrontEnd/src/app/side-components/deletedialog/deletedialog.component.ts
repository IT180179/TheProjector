import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../projects-overview/projects-overview.component";
import {HttpClient} from "@angular/common/http";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DataService} from "../../services/data.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-deletedialog',
  templateUrl: './deletedialog.component.html',
  styleUrls: ['./deletedialog.component.scss']
})
export class DeletedialogComponent implements OnInit {

  meilenstein: any;

  constructor(private _router: Router, private dataService: DataService, private snackBar: MatSnackBar, public dialogRef: MatDialogRef<DeletedialogComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData,
              public http: HttpClient, public service: HttpService) {}
  ngOnInit() {
    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }
    // GET => Meilenstein-Infos
    this.service.getMilestonesByID(Number(this.data)).subscribe({
      next: value => {
        // console.log(value)
        this.meilenstein = value
      }, error: err => {}
    });
  }
  onDelete() {
    // DELETE - Meilensteinhistorie, da sonst Meilenstein nicht gelöscht werden kann
    this.service.setMeilensteinHistorie(this.data.id).subscribe({
      next: value => {
        // console.log(value)
        this.snackBar.open(`Daten konnten gel werden`, undefined, {duration: 300, panelClass: 'snackbar-dark'});

      }, error: err => {
        this.snackBar.open(`Daten konnten nicht gelöscht werden ${err.message}`, undefined, {
          duration: 300,
          panelClass: 'snackbar-dark'
        });
      }
    });
    // DELETE - Meilenstein
    this.service.deleteMeilenstein(this.data.id).subscribe({
      next: value => {
        //  console.log(value)
        this.snackBar.open(`Meilenstein wurde gelöscht`, undefined, {
          duration: 300,
          panelClass: 'snackbar-dark'
        });
      }, error: err => {
        this.snackBar.open(`Daten konnten nicht gelöscht werden ${err.message}`, undefined, {
          duration: 300,
          panelClass: 'snackbar-dark'
        });

      }
    });
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
