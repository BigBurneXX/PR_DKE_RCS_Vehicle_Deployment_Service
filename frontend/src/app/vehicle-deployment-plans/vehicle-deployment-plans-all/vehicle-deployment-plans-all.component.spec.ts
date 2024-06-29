import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlansAllComponent } from './vehicle-deployment-plans-all.component';

describe('VehicleDeploymentPlanComponent', () => {
  let component: VehicleDeploymentPlansAllComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlansAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlansAllComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehicleDeploymentPlansAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
