import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewVehicleDeploymentPlanningComponent } from './new-vehicle-deployment-planning.component';

describe('NewVehicleDeploymentPlanningComponent', () => {
  let component: NewVehicleDeploymentPlanningComponent;
  let fixture: ComponentFixture<NewVehicleDeploymentPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewVehicleDeploymentPlanningComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewVehicleDeploymentPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
