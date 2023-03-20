import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../projects-overview/projects-overview.component";
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-milestone-historie',
  templateUrl: './milestone-historie.component.html',
  styleUrls: ['./milestone-historie.component.scss']
})
export class MilestoneHistorieComponent implements OnInit {
  constructor(private _router: Router, private snackBar: MatSnackBar, public dialogRef: MatDialogRef<MilestoneHistorieComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData,
              public service: HttpService, public dataService: DataService) {
  }

  milestonesHistorie: any;

  ngOnInit(): void {
    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }
    this.milestonesHistorie = this.service.getHistorieByMilestones(this.data).subscribe({
        next: value => {
          if (value.length == 0) {
            this.onNoClick()
          }
          this.milestonesHistorie = value
        }, error: err => {}
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
}
