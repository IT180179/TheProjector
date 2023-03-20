import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeProjectFormComponent } from './change-project-form.component';

describe('ChangeProjectFormComponent', () => {
  let component: ChangeProjectFormComponent;
  let fixture: ComponentFixture<ChangeProjectFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeProjectFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeProjectFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
