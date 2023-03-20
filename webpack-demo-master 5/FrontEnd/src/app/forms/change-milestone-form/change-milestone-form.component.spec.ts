import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeMilestoneFormComponent } from './change-milestone-form.component';

describe('ChangeMilestoneFormComponent', () => {
  let component: ChangeMilestoneFormComponent;
  let fixture: ComponentFixture<ChangeMilestoneFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeMilestoneFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeMilestoneFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
