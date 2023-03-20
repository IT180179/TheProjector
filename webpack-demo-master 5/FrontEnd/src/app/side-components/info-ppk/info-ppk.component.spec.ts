import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPpkComponent } from './info-ppk.component';

describe('InfoPpkComponent', () => {
  let component: InfoPpkComponent;
  let fixture: ComponentFixture<InfoPpkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfoPpkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoPpkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
