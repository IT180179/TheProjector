import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GaesteListComponent } from './gaeste-list.component';

describe('GaesteListComponent', () => {
  let component: GaesteListComponent;
  let fixture: ComponentFixture<GaesteListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GaesteListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GaesteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
