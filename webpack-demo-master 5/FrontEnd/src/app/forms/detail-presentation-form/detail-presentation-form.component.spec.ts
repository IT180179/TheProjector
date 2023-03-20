import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailPresentationFormComponent } from './detail-presentation-form.component';

describe('DetailPresentationFormComponent', () => {
  let component: DetailPresentationFormComponent;
  let fixture: ComponentFixture<DetailPresentationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailPresentationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailPresentationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
