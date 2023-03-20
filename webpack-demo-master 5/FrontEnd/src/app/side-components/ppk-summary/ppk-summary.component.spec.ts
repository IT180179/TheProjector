import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PPKSummaryComponent } from './ppk-summary.component';

describe('PPKSummaryComponent', () => {
  let component: PPKSummaryComponent;
  let fixture: ComponentFixture<PPKSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PPKSummaryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PPKSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
