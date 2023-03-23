import {Component, Inject, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {DataService} from "../../services/data.service";
import {SummaryComponent} from "../../side-components/summary/summary.component";
import {Router} from "@angular/router";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";

import {DomSanitizer} from "@angular/platform-browser";
import {DialogData} from "../../side-components/projects-overview/projects-overview.component";
import {HttpService} from "../../services/http.service";
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
              public fb: FormBuilder,public httpService: HttpService, public dataService: DataService, private snackBar: MatSnackBar, @Inject(MAT_DIALOG_DATA) public data: DialogData) { }


  ngOnInit(): void {
    // @ts-ignore
    this.freitext = this.data[1].freitext;
    // @ts-ignore
    this.beschreibung = this.data[1].beschreibung;

    console.log(this.data)

    if(!this.dataService.isloggedIn){
      this._router.navigate(['**']);
    }
    this.ppk_id = this.dataService.getppk_id()
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
            ppk_id: Number(this.dataService.getppk_id())
          },
          projekte_id:{
            projekt_id: Number(this.dataService.projekt_id)
          }}
      },
      titel: value.beschreibung,
      freitext: value.freitext,
      upload: this.base64 || null,
      beschreibung: value.beschreibung
    }

    console.log(this.newdata)
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
    this.fileSelected = files.target.files[0]
    this.imageUrl = this.sant.bypassSecurityTrustUrl(window.URL.createObjectURL(this.fileSelected)) as string;
    this.convertFileToBase64();
  }

  convertFileToBase64(): void{
    let reader = new FileReader()
    reader.readAsDataURL(this.fileSelected);
    reader.onloadend = () => {
      this.base64 = reader.result as string;
      console.log(this.base64)
    }

    //this.blob = this.convertToBlob(this.base64);
    //console.log(this.blob)

    //const resizedBlob = this.resizeBlob(this.blob, 300, 300);
    //console.log(resizedBlob)
  }

  convertToBlob(imageData: any): Blob {
    console.log("hi")
    const byteString = atob(imageData.split(',')[1]);
    const mimeString = imageData.split(',')[0].split(':')[1].split(';')[0];
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);

    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ab], { type: mimeString });

  }

  async resizeBlob(blob: Blob, maxWidth: number, maxHeight: number): Promise<Blob> {
    return new Promise((resolve, reject) => {
      const img = new Image();
      img.src = URL.createObjectURL(blob);
      img.onload = () => {
        const canvas = document.createElement('canvas');
        let width = img.width;
        let height = img.height;

        if (width > maxWidth) {
          height *= maxWidth / width;
          width = maxWidth;
        }

        if (height > maxHeight) {
          width *= maxHeight / height;
          height = maxHeight;
        }

        canvas.width = width;
        canvas.height = height;

        const ctx = canvas.getContext('2d');
        // @ts-ignore
        ctx.drawImage(img, 0, 0, width, height);

        canvas.toBlob((resizedBlob) => {
          // @ts-ignore
          resolve(resizedBlob);
        }, 'image/jpeg', 0.7);
      };
      img.onerror = (error) => reject(error);
    });
  }
  selectedFile: File | undefined;

  onFileSelectedNew(event: Event) {
    // @ts-ignore
    this.fileSelected = event.target.files[0]
    this.imageUrl = this.sant.bypassSecurityTrustUrl(window.URL.createObjectURL(this.fileSelected)) as string;
      // @ts-ignore
    this.selectedFile = event.target.files[0] as File;

    const image = new Image();
    // @ts-ignore
    image.src = URL.createObjectURL(event.target.files[0]);

    image.onload = () => {
      const canvas = document.createElement('canvas');
      const ctx = canvas.getContext('2d');
      canvas.width = 400; // Set the desired width
      canvas.height = (image.height / image.width) * canvas.width;

      // Draw the image on the canvas
      // @ts-ignore
      ctx.drawImage(image, 0, 0, canvas.width, canvas.height);

      // Convert canvas to blob
      canvas.toBlob((blob) => {
        // Send the blob to the server
        // @ts-ignore
        this.sendImage(blob);
      }, 'image/jpeg', 0.7);
    };
  }

  byteArray: any

  sendImage(blob: Blob) {
    const formData = new FormData();
    console.log(blob)
    formData.append('image', blob, 'image.jpg');
    console.log(formData)
    console.log(blob)
    this.downloadImage(blob)

    const reader = new FileReader();

    reader.onload = () => {
      this.byteArray = new Uint8Array(reader.result as ArrayBuffer);
      console.log(this.byteArray); // byteArray enthält die Bytes des Blobs als Uint8Array
    };
    reader.readAsArrayBuffer(blob);

   /** this.newdata={
      "titel": "string",
      "untertitel": "string",
      "beschreibung": "string",
      "freitext": "string",
      "bild": this.byteArray,
      "ppk_projekte_id": {
        "ppk_projekte_id": {
          "ppk_id": {
            "ppk_id": 0,
          },
          "projekte_id": {
            "projekt_id": 0,
          }}}};**/

    this.httpService.postFreieFolie(this.newdata).subscribe(
       (response) => {
         console.log('Image uploaded successfully', response);
        }, (error) => {
         console.error('Error uploading image', error);
        }
        );
  }

  downloadImage(blob: Blob): void {
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'image.jpg';
    link.click();
  }
}


