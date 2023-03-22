import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { NavbarComponent } from './side-components/navbar/navbar.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { ProjectsOverviewComponent } from './side-components/projects-overview/projects-overview.component';
import { ProjectDetailComponent } from './side-components/project-detail/project-detail.component';
import { MilestoneListComponent } from './side-components/milestone-list/milestone-list.component';
import { EmployeeListComponent } from './side-components/employee-list/employee-list.component';
import { MilestoneHistorieComponent } from './side-components/milestone-historie/milestone-historie.component';
import { FooterComponent } from './side-components/footer/footer.component';
import { StartMenuComponent } from './side-components/start-menu/start-menu.component';
import { CreateProjectFormComponent } from './forms/create-project-form/create-project-form.component';
import { CreateEmployeeFormComponent } from './forms/create-employee-form/create-employee-form.component';
import { AddEmployeeFormComponent } from './forms/add-employee-form/add-employee-form.component';
import { AddMilestoneFormComponent } from './forms/add-milestone-form/add-milestone-form.component';
import {MAT_DATE_LOCALE, MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import { MatDatepickerModule} from '@angular/material/datepicker';
import { SoftwareRequestsFormComponent } from './forms/software-requests-form/software-requests-form.component';
import { MatCheckboxModule} from "@angular/material/checkbox";
import { ChangeProjectFormComponent } from './forms/change-project-form/change-project-form.component';
import { PPKPresentationFormComponent } from './forms/ppkpresentation-form/ppkpresentation-form.component';
import { CreatePPKFormComponent } from './forms/create-ppk-form/create-ppkform.component';
import { PPMenuComponent } from './side-components/pp-menu/pp-menu.component';
import { ChangeMilestoneFormComponent } from './forms/change-milestone-form/change-milestone-form.component';
import { FreieFolieFormComponent } from './forms/freie-folie-form/freie-folie-form.component';
import { DetailPresentationFormComponent } from './forms/detail-presentation-form/detail-presentation-form.component';
import { BeschlussFolieFormComponent } from './forms/beschluss-folie-form/beschluss-folie-form.component';
import { MatTooltipModule} from "@angular/material/tooltip";
import { MatSnackBarModule} from '@angular/material/snack-bar';
import { InfoPpkComponent } from './side-components/info-ppk/info-ppk.component';
import { BackButtonDirective } from './services/back-button.directive';
import { MyProjectsComponent } from './side-components/my-projects/my-projects.component';
import { UserProfileComponent } from './side-components/user-profile/user-profile.component';
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {MatStepperModule} from "@angular/material/stepper";
import { GaesteListComponent } from './side-components/gaeste-list/gaeste-list.component';
import { PPKSummaryComponent } from './side-components/ppk-summary/ppk-summary.component';
import {CommonModule, DatePipe} from "@angular/common";
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { SummaryComponent } from './side-components/summary/summary.component';
import { ResourceFormComponent } from './forms/resource-form/resource-form.component';
import { DeletedialogComponent } from './side-components/deletedialog/deletedialog.component';
import { PpkInformationComponent } from './side-components/ppk-information/ppk-information.component';
import { EmployeeMenuComponent } from './side-components/employee-menu/employee-menu.component';
import { ResourcenOverviewComponent } from './side-components/resourcen-overview/resourcen-overview.component';
import { EmNavComponent } from './side-components/em-nav/em-nav.component';
import { LoginComponent } from './login/login.component';
import {AuthInterceptor} from "./services/auth-interceptor";
import { PageNotFoundComponent } from './side-components/page-not-found/page-not-found.component';
import { PpkUpdateComponent } from './forms/ppk-update/ppk-update.component';
import { PpkListeComponent } from './side-components/ppk-liste/ppk-liste.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProjectsOverviewComponent,
    ProjectDetailComponent,
    MilestoneListComponent,
    EmployeeListComponent,
    MilestoneHistorieComponent,
    FooterComponent,
    StartMenuComponent,
    CreateProjectFormComponent,
    CreateEmployeeFormComponent,
    AddEmployeeFormComponent,
    AddMilestoneFormComponent,
    SoftwareRequestsFormComponent,
    ChangeProjectFormComponent,
    PPKPresentationFormComponent,
    CreatePPKFormComponent,
    PPMenuComponent,
    ChangeMilestoneFormComponent,
    FreieFolieFormComponent,
    DetailPresentationFormComponent,
    BeschlussFolieFormComponent,
    InfoPpkComponent,
    BackButtonDirective,
    MyProjectsComponent,
    UserProfileComponent,
    GaesteListComponent,
    PPKSummaryComponent,
    SummaryComponent,
    ResourceFormComponent,
    DeletedialogComponent,
    PpkInformationComponent,
    EmployeeMenuComponent,
    ResourcenOverviewComponent,
    EmNavComponent,
    LoginComponent,
    PageNotFoundComponent,
    PpkUpdateComponent,
    PpkListeComponent

  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    ReactiveFormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRippleModule,
    MatCheckboxModule,
    MatIconModule,
    MatTooltipModule,
    MatSnackBarModule,
    FormsModule,
    MatDialogModule,
    MatStepperModule,
    CommonModule
  ],
  providers: [
    PPKPresentationFormComponent, LoginComponent,FreieFolieFormComponent,
    {provide: MAT_DATE_LOCALE, useValue: 'de-AT'},
    {
      provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor,
      multi: true
    },
    DatePipe
  ]
  ,
  bootstrap: [AppComponent]
})
export class AppModule { }
