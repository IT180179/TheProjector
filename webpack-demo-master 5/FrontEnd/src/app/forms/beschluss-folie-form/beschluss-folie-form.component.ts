import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DialogData } from '../../side-components/projects-overview/projects-overview.component';
import { DataService } from '../../services/data.service';
import { SummaryComponent } from '../../side-components/summary/summary.component';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-beschluss-folie-form',
  templateUrl: './beschluss-folie-form.component.html',
  styleUrls: ['./beschluss-folie-form.component.scss']
})
export class BeschlussFolieFormComponent implements OnInit {
  ppk_id: any;
  data: any;
  newdata: any;
  titel:any;
  freitext:any;
  entscheidung:any;
  entscheidungBool:any;

  addressForm = this.fb.group({
    freitext: [null, Validators.compose([Validators.maxLength(1046), Validators.required])],
    titel: [null, Validators.compose([Validators.maxLength(1046), Validators.required])],
    entscheidung: [null]
  });

  constructor(
    private router: Router,
    private _router: Router,
    private snackBar: MatSnackBar,
    public dialog: MatDialog,
    public dataService: DataService,
    private http: HttpClient,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<any>,
    @Inject(MAT_DIALOG_DATA) public id: DialogData
  ) {}

  ngOnInit(): void {
    try {
      // @ts-ignore
      console.log(this.id[1].entscheidung)
      // @ts-ignore
      this.titel = this.id[1].titel;
      // @ts-ignore
      this.freitext = this.id[1].freitext;
      // @ts-ignore
      this.entscheidung = this.id[1].entscheidung;

      if(this.entscheidung == 0){
        this.entscheidungBool = false;
      }else{
        this.entscheidungBool = true;
      }
    } catch (e){
    console.log("test");
  }
    // Überprüfen ob User eingeloggt ist
    if (!this.data.isloggedIn) {
      this.router.navigate(['**']);
    }
    this.ppk_id = this.dataService.ppk_id;
  }

  onSubmit(data: any) {
    if (data.entscheidung) {
      data.entscheidung = 1;
    } else {
      data.entscheidung = 0;
    }

    // JSON-Objekt für POST
    this.newdata = {
      titel: data.titel,
      freitext: data.freitext,
      entscheidung: data.entscheidung,
      upload: null,
      ppk_projekte_id: {
        ppk_projekte_id: {
          ppk_id: {
            ppk_id: 1
          },
          projekte_id: {
            projekt_id: Number(this.dataService.projekt_id)
          }
        }
      }
    };

    this.openSummary();
    this.closeDialog();
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
    this.dialogRef.close({ data: this.newdata });
  }
}
