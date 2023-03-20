import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PPMenuComponent } from './pp-menu.component';

describe('PPMenuComponent', () => {
  let component: PPMenuComponent;
  let fixture: ComponentFixture<PPMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PPMenuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PPMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
