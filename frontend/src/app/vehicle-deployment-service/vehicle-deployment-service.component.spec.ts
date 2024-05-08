import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleDeploymentServiceComponent } from './vehicle-deployment-service.component';

describe('VehicleDeploymentServiceComponent', () => {
  let component: VehicleDeploymentServiceComponent;
  let fixture: ComponentFixture<VehicleDeploymentServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleDeploymentServiceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehicleDeploymentServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
