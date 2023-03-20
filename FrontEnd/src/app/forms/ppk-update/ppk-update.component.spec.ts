import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PpkUpdateComponent } from './ppk-update.component';

describe('PpkUpdateComponent', () => {
  let component: PpkUpdateComponent;
  let fixture: ComponentFixture<PpkUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PpkUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PpkUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
