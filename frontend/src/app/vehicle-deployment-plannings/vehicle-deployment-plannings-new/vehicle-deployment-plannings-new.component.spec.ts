import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanningsNewComponent } from './vehicle-deployment-plannings-new.component';

describe('NewVehicleDeploymentPlanningComponent', () => {
  let component: VehicleDeploymentPlanningsNewComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanningsNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanningsNewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentPlanningsNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
