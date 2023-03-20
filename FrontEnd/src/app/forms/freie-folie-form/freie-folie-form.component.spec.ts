import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreieFolieFormComponent } from './freie-folie-form.component';

describe('FreieFolieFormComponent', () => {
  let component: FreieFolieFormComponent;
  let fixture: ComponentFixture<FreieFolieFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FreieFolieFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FreieFolieFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
