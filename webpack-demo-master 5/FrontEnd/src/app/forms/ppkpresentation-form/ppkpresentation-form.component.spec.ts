import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PPKPresentationFormComponent } from './ppkpresentation-form.component';

describe('PPKPresentationFormComponent', () => {
  let component: PPKPresentationFormComponent;
  let fixture: ComponentFixture<PPKPresentationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PPKPresentationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PPKPresentationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
