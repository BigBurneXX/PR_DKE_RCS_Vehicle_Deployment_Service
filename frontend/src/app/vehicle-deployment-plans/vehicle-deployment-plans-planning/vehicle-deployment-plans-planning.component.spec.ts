import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlansPlanningComponent } from './vehicle-deployment-plans-planning.component';

describe('VehicleDeploymentPlanningDetailsComponent', () => {
  let component: VehicleDeploymentPlansPlanningComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlansPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlansPlanningComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentPlansPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
