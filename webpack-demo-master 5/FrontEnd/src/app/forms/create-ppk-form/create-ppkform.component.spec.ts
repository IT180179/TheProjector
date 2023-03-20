import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePPKFormComponent } from './create-ppkform.component';

describe('CreatePPKFormComponent', () => {
  let component: CreatePPKFormComponent;
  let fixture: ComponentFixture<CreatePPKFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePPKFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePPKFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
