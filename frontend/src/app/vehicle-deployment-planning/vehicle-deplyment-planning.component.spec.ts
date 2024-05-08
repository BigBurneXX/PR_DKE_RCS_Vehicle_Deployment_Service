import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeplymentPlanningComponent } from './vehicle-deplyment-planning.component';

describe('VehicleDeplymentPlanningComponent', () => {
  let component: VehicleDeplymentPlanningComponent;
  let fixture: ComponentFixture<VehicleDeplymentPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeplymentPlanningComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehicleDeplymentPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
