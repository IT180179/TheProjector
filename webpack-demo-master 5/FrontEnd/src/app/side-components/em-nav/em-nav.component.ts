import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-em-nav',
  templateUrl: './em-nav.component.html',
  styleUrls: ['./em-nav.component.scss']
})
export class EmNavComponent implements OnInit {

  constructor(public dataservice: DataService) {
  }
  ngOnInit(): void {

  }
}
