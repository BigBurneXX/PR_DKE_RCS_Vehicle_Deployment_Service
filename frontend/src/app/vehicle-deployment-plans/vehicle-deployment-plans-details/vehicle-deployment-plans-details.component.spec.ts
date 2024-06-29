import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentPlansDetailsComponent } from './vehicle-deployment-plans-details.component';

describe('VehicleDeploymentPlanDetailsComponent', () => {
  let component: VehicleDeploymentPlansDetailsComponent;
  let fixture: ComponentFixture<VehicleDeploymentPlansDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentPlansDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentPlansDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
