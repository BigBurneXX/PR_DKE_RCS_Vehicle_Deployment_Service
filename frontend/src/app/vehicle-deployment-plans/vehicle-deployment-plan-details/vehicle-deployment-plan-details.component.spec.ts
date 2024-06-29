import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanDetailsComponent } from './vehicle-deployment-plan-details.component';

describe('VehicleDeploymentPlanDetailsComponent', () => {
  let component: VehicleDeploymentPlanDetailsComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentPlanDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
