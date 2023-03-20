import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourcenOverviewComponent } from './resourcen-overview.component';

describe('ResourcenOverviewComponent', () => {
  let component: ResourcenOverviewComponent;
  let fixture: ComponentFixture<ResourcenOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResourcenOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResourcenOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
