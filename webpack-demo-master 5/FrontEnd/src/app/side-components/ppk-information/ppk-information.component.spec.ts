import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PpkInformationComponent } from './ppk-information.component';

describe('PpkInformationComponent', () => {
  let component: PpkInformationComponent;
  let fixture: ComponentFixture<PpkInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PpkInformationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PpkInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
