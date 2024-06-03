import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateVehicleDeploymentPlanningComponent } from './create-vehicle-deployment-planning.component';

describe('VehicleDeploymentPlanningListComponent', () => {
  let component: CreateVehicleDeploymentPlanningComponent;
  let fixture: ComponentFixture<CreateVehicleDeploymentPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateVehicleDeploymentPlanningComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateVehicleDeploymentPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
