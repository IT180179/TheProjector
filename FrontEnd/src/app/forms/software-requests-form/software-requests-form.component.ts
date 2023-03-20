import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {DataService} from "../../services/data.service";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {PPKPresentationFormComponent} from "../ppkpresentation-form/ppkpresentation-form.component";
import {DialogData} from "../../side-components/projects-overview/projects-overview.component";

@Component({
  selector: 'app-software-requests-form',
  templateUrl: './software-requests-form.component.html',
  styleUrls: ['./software-requests-form.component.scss']
})
export class SoftwareRequestsFormComponent implements OnInit {
  addressForm = this.fb.group({
    phasen_id: [null],
    status: [null, Validators.compose([Validators.maxLength(295), Validators.required])],
    beschreibung: [null, Validators.compose([Validators.maxLength(295), Validators.required])]
  });
  
  auswahl: any
  selectedValue: any
  phasen: any | null;
  newdata: any
  phase: any;
  checkbox: any;
  anforderungsprozess: any | null;
  selectedAuswahl = [0,0,0,0,0,0,0,0];
  beschreibung:any|null;
  status:any=1;


  constructor(private snackBar: MatSnackBar, private dialogRef: MatDialogRef<any>, public dataService: DataService,
              public service: HttpService, private _router: Router, public dialog: MatDialog, private http: HttpClient,
              private fb: FormBuilder,private ppk: PPKPresentationFormComponent,  @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }
  ngOnInit() {
    try{
      // @ts-ignore
      this.selectedAuswahl = this.data[1].auswahl;
      // @ts-ignore
      this.beschreibung = this.data[1].beschreibung;

      // @ts-ignore
      this.anforderungsprozess = this.data[1].anforderungsprozess;

      // @ts-ignore
      this.status = this.data[1].status;
      console.log(this.status)

    }catch (e){
      console.log("test");
    }


    console.log(this.data);
    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }
    this.phasen = this.service.getPhasen().subscribe({
      next: value => {
        this.phasen = value
      }, error: err => {}
    });
    console.log(this.phasen + " phasen");
  }
  getCheckboxValue() {
    this.auswahl = [];
    for (let i = 0; i <= 7; i++) {
      const checkbox = document.getElementById("checkbox" + i) as HTMLInputElement | null;
      if (checkbox?.checked) {
        // console.log('Checkbox is checked');
        this.auswahl.push(1);
        // console.log(1)
      } else {
        // console.log('Checkbox is NOT checked');
        this.auswahl.push(0);
      }
      console.log(checkbox?.checked);
    }
    console.log(this.auswahl)
  }

  onSubmit(value: any) {
    this.getCheckboxValue()
    this.newdata = {
      status: Number(value.phasen_id),
      beschreibung: value.beschreibung,
      anforderungsprozess: value.status,
      projekte_id: {
        projekt_id: this.dataService.projekt_id
      },
      auswahl: this.auswahl
    }
    console.log(this.newdata)
    console.log(this.auswahl[1] + '/' + this.auswahl[3] + '/' + this.auswahl[3] + '/' +
      this.auswahl[4] + '/' + this.auswahl[5] + '/' + this.auswahl[6] + '/' + this.auswahl[7])

    this.openSummary()
    this.closeDialog()
  }
  openSummary() {
    const dialogRef = this.dialog.open(SummaryComponent, {
      width: '400px',
      height: '250px'
    });
    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed');
    });
  }
  closeDialog() {
    this.dialogRef.close({data:this.newdata});
  }

  getStatusById(id:number):any{
    let phase = this.service.getPhasenPerID(id).subscribe((value)=>{
      phase = value;
    })
    return phase;
  }
}

