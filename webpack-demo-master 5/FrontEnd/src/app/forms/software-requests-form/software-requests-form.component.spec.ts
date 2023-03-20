import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SoftwareRequestsFormComponent } from './software-requests-form.component';

describe('SoftwareRequestsFormComponent', () => {
  let component: SoftwareRequestsFormComponent;
  let fixture: ComponentFixture<SoftwareRequestsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SoftwareRequestsFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SoftwareRequestsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
