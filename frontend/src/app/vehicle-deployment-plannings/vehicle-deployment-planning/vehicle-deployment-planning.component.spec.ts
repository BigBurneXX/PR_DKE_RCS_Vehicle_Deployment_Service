import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanningComponent } from './vehicle-deployment-planning.component';

describe('VehicleDeploymentPlanningListComponent', () => {
  let component: VehicleDeploymentPlanningComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanningComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehicleDeploymentPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
