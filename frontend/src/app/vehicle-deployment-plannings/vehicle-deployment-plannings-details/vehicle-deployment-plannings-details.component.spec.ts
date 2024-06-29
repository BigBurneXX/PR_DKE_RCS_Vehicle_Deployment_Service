import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanningsDetailsComponent } from './vehicle-deployment-plannings-details.component';

describe('VehicleDeploymentPlanningsDetailsComponent', () => {
  let component: VehicleDeploymentPlanningsDetailsComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanningsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanningsDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentPlanningsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
