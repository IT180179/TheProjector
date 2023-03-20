import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['../../../styles.scss']
})


export class NavbarComponent implements OnInit {

  constructor(public dataservice: DataService) {}
  ngOnInit(): void {
  }

}
