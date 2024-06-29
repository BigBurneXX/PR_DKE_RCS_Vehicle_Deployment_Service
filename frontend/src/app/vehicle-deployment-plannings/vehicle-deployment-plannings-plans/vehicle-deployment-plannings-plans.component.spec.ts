import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanningsPlansComponent } from './vehicle-deployment-plannings-plans.component';

describe('VehicleDeploymentPlanningDetailsComponent', () => {
  let component: VehicleDeploymentPlanningsPlansComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanningsPlansComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanningsPlansComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentPlanningsPlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
