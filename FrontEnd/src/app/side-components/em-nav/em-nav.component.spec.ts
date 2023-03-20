import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmNavComponent } from './em-nav.component';

describe('EmNavComponent', () => {
  let component: EmNavComponent;
  let fixture: ComponentFixture<EmNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmNavComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
