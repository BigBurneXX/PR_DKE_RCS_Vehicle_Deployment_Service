import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanComponent } from './vehicle-deployment-plan.component';

describe('VehicleDeploymentPlanComponent', () => {
  let component: VehicleDeploymentPlanComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehicleDeploymentPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
