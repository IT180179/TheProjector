import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogData} from "../projects-overview/projects-overview.component";


@Component({
  selector: 'app-ppk-summary',
  templateUrl: './ppk-summary.component.html',
  styleUrls: ['./ppk-summary.component.scss']
})
export class PPKSummaryComponent implements OnInit {

   data: any;

  constructor(public dialogRef: MatDialogRef<any>,
              @Inject(MAT_DIALOG_DATA) data: DialogData) {
    this.data = data;
  }

  ngOnInit(): void {
  }

}
