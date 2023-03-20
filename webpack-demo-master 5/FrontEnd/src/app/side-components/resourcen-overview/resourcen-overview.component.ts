import { Component, OnInit } from '@angular/core';
import {HttpService} from "../../services/http.service";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-resourcen-overview',
  templateUrl: './resourcen-overview.component.html',
  styleUrls: ['./resourcen-overview.component.scss']
})
export class ResourcenOverviewComponent implements OnInit {

  constructor(private http: HttpService, private dataService: DataService) { }
  id: any
  resourcen: any

  ngOnInit(): void {
    this.id = this.dataService.user_id
    this.resourcen = this.http.getArbeitszeiten(this.id).subscribe({
      next: value => {
        console.log(value)
        this.resourcen = value
      }, error: err => {}
    });
  }

  translateRoles(rechte:number):string{
    switch (rechte){
      case 1:
        return "Projektmanager";
        break;
      case 2:
        return "Fachkoordinator";
        break;
      case 3:
        return "Mitarbeiter";
        break;
      case 4:
        return "Stakeholder";
        break;
      case 5:
        return "Auftraggeber";
        break;
      default:
        return "Invalid"

    }
  }
}
