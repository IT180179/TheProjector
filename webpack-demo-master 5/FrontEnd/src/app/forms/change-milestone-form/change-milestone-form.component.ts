import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ChangeMeilenstein} from "../../model/ChangeMeilenstein";
import {DataService} from "../../services/data.service";
import {Meilenstein_Histories} from "../../model/Meilenstein_Histories";
import {DatePipe} from "@angular/common";
import {Router} from "@angular/router";
import {HttpService} from "../../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-change-milestone-form',
  templateUrl: './change-milestone-form.component.html',
  styleUrls: ['./change-milestone-form.component.scss']
})
export class ChangeMilestoneFormComponent implements OnInit {

  addressForm = this.fb.group({
    titel: [null],
    status: [null],
    end_datum: [null],
    beschreibung: [null],
    aenderung: [null, Validators.required]
  });

  constructor(private router: Router, private snackBar: MatSnackBar,
              private _router: Router, public datePipe: DatePipe, private http: HttpClient, private fb: FormBuilder,
              public service: HttpService, public dataService: DataService) {
  }

  today: Date = new Date();
  milestones: any;
  selectedValue: any;
  projekt_id: any;
  milestone: any;
  meilenstein_id: any;
  data: any;
  newdata: ChangeMeilenstein | undefined;
  newhistorie: Meilenstein_Histories | undefined;
  todayDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');

  ngOnInit(): void {
    this.projekt_id = this.dataService.projekt_id;
    this.meilenstein_id = this.dataService.milestone_id

    //Überprüfen ob der User eingeloggt ist
    if(!this.dataService.isloggedIn){
      this.router.navigate(['**']);
    }

    //GET Daten vom Meilenstein
    this.milestone = this.service.getMilestonesByID(this.meilenstein_id).subscribe({
      next: value => {
        // console.log(value)
        this.milestone = value
      }, error: err => {
        this.snackBar.open(`Meilenstein konnte nicht geladen werden: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
      }
    });

    //GET Meilensteine
    this.milestones = this.service.getMilestones().subscribe({
      next: value => {
        // console.log(value)
        this.milestones = value
      }, error: err => {
        this.snackBar.open(`Rolen konnten nicht geladen werden: ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
      }
    });
  }

  onSubmit(data: any) {
    //JSON für das POST
    this.newhistorie = {
      meilenstein_id: {
        meilensteine_id: this.meilenstein_id
      },
      aenderung: data.aenderung,
      alter_status: this.milestone.status,
      datum: this.milestone.start_datum,
      end_datum: data.end_datum
    }

    //POST-Meilenstein Historie
    this.http.post('http://localhost:8080/meilenstein_histories/add', this.newhistorie)
      .subscribe({
        next: value => {
          // console.log(value)
          this.snackBar.open(`Daten wurden GESPEICHERT`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
        }, error: err => {
          this.snackBar.open(`Daten konnten nicht hinzugefügt werden ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
        }
      });

    //JSON für das UPDATE
    this.newdata = {
      meilensteine_id: this.meilenstein_id ||this.milestone.meilenstein_id,
      titel: data.titel || this.milestone.titel,
      beschreibung: data.beschreibung || this.milestone.beschreibung,
      status: data.status,
      end_datum: data.end_datum || this.milestone.end_datum,
      projekt_id: {
        projekt_id: this.projekt_id
      }
    }
    //UPDATE des Meilensteins
    this.http.put('http://localhost:8080/meilensteine/update', this.newdata)
      .subscribe({
        next: value => {
          // console.log(value)
          this.snackBar.open(`Daten wurden GESPEICHERT`, undefined, {duration: 300, panelClass: 'snackbar-dark'});
        }, error: err => {
          this.snackBar.open(`Daten konnten nicht geändert werden ${err.message}`, undefined, {duration: 300, panelClass: 'snackbar-light'});
        }
      });
    this.close()
  }
  close() {
    this._router.navigate(['/milestone_list']);
  }
}
