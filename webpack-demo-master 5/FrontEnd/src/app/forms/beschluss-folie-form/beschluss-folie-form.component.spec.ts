import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeschlussFolieFormComponent } from './beschluss-folie-form.component';

describe('BeschlussFolieFormComponent', () => {
  let component: BeschlussFolieFormComponent;
  let fixture: ComponentFixture<BeschlussFolieFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BeschlussFolieFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeschlussFolieFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
