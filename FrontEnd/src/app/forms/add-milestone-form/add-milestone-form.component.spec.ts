import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMilestoneFormComponent } from './add-milestone-form.component';

describe('AddMilestoneFormComponent', () => {
  let component: AddMilestoneFormComponent;
  let fixture: ComponentFixture<AddMilestoneFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMilestoneFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMilestoneFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
