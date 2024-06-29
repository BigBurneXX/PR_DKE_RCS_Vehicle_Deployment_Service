import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanningDetailsComponent } from './vehicle-deployment-planning-details.component';

describe('VehicleDeploymentPlanningDetailsComponent', () => {
  let component: VehicleDeploymentPlanningDetailsComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanningDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanningDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentPlanningDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
