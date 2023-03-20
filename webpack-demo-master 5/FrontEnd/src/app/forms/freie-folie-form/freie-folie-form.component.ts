import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DataService} from "../../services/data.service";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {PPKPresentationFormComponent} from "../ppkpresentation-form/ppkpresentation-form.component";
import * as pako from 'pako';


import {DomSanitizer} from "@angular/platform-browser";
import {DialogData} from "../../side-components/projects-overview/projects-overview.component";
@Component({
  selector: 'app-freie-folie-form',
  templateUrl: './freie-folie-form.component.html',
  styleUrls: ['./freie-folie-form.component.scss']
})
export class FreieFolieFormComponent implements OnInit {
  imageSrc: string | undefined;
  ppk_id: any;
  projekt_id: any;
  newdata: any
  freitext:any;
  beschreibung:any;
  fileName='';


  myForm = this.fb.group({
    beschreibung: [null,  Validators.compose([Validators.maxLength(955), Validators.required])],
    freitext: [null,  Validators.compose([Validators.maxLength(955), Validators.required])],
    upload: [''],
    fileSource:  ['']
  });
  constructor(private sant: DomSanitizer, private dialogRef: MatDialogRef<any>,private _router: Router, public dialog: MatDialog,private http:HttpClient,
              public fb: FormBuilder, public dataService: DataService, private snackBar: MatSnackBar, @Inject(MAT_DIALOG_DATA) public data: DialogData) { }


  ngOnInit(): void {
    // @ts-ignore
    this.freitext = this.data[1].freitext;
    // @ts-ignore
    this.beschreibung = this.data[1].beschreibung;

    console.log(this.data)

    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }
    this.ppk_id = this.dataService.ppk_id
    this.projekt_id = this.dataService.projekt_id
  }
  get f(){
    return this.myForm.controls;
  }

  onFileSelected(event:any) {

    const file:File = event.target.files[0];

    if (file) {

      this.fileName = file.name;

      const formData = new FormData();

      formData.append("thumbnail", file);
      console.log(formData.get("thumbnail"));
      //const upload$ = this.http.post("/api/thumbnail-upload", formData);


      //upload$.subscribe();

    }

  }


  onFileChange(event: any) {
    const reader = new FileReader();
    if(event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageSrc = reader.result as string;
        this.myForm.patchValue({fileSource: reader.result});
      };
    }
  }

  submit(value: any){
    this.newdata = {
      ppk_projekte_id:{
        ppk_projekte_id:{
          ppk_id:{
            ppk_id: Number(this.ppk_id)
          },
          projekte_id:{
            projekt_id: Number(this.projekt_id)
          }}
      },
      titel: value.beschreibung,
      freitext: value.freitext,
      upload: this.base64,
      beschreibung: value.beschreibung
    }
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

  closeDialog(){
    this.dialogRef.close({data:this.newdata});
  }


  base64: string = "Base64 ..."
  fileSelected?: any;
  imageUrl?: any;
  base64textString = [];

  onSelectNewFile(files: any) {
    this.fileSelected = files.files[0];
    this.imageUrl = this.sant.bypassSecurityTrustUrl(window.URL.createObjectURL(this.fileSelected)) as string;
    // console.log(this.base64)
    this.convertFileToBase64();
    // console.log(this.base64)

    const compressedData = pako.deflate(this.base64);
    const compressedBase64 = btoa(String.fromCharCode(...new Uint8Array(compressedData)));
    console.log(compressedBase64);
  }

  convertFileToBase64(): void{
    let reader = new FileReader()
    reader.readAsDataURL(this.fileSelected as Blob);
    reader.onloadend = () => {
      this.base64 = reader.result as string;
      //console.log(this.base64)
    }
  }
}


