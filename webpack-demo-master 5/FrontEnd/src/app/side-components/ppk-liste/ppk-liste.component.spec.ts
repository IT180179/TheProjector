import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PpkListeComponent } from './ppk-liste.component';

describe('PpkListeComponent', () => {
  let component: PpkListeComponent;
  let fixture: ComponentFixture<PpkListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PpkListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PpkListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
