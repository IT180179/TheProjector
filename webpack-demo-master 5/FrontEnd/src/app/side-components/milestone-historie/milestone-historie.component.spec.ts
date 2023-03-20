import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MilestoneHistorieComponent } from './milestone-historie.component';

describe('MilestoneHistorieComponent', () => {
  let component: MilestoneHistorieComponent;
  let fixture: ComponentFixture<MilestoneHistorieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MilestoneHistorieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MilestoneHistorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
