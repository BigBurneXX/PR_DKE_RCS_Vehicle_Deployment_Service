import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlanningsAllComponent } from './vehicle-deployment-plannings-all.component';

describe('VehicleDeploymentPlanningListComponent', () => {
  let component: VehicleDeploymentPlanningsAllComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlanningsAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlanningsAllComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehicleDeploymentPlanningsAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
