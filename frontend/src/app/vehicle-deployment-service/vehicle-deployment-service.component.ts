import { Component } from '@angular/core';
import { VehicleDeploymentService } from "../model/VehicleDeploymentService";
import { VehicleDeploymentServiceService } from "../services/vehicle-deployment-service.service";
import { VehicleDeploymentPlan } from "../model/VehicleDeploymentPlan";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-vehicle-deployment-service',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './vehicle-deployment-service.component.html',
  styleUrl: './vehicle-deployment-service.component.css'
})
export class VehicleDeploymentServiceComponent {
  vehicleDeploymentService: VehicleDeploymentService = {
    id: null,
    serviceName: '',
    description: '',
    vehicleDeploymentPlannings: []
  };

  newVehicleDeploymentPlan: VehicleDeploymentPlan = {
    id: null,
    // Set default values or leave them blank
  };

  constructor(private vehicleDeploymentServiceService: VehicleDeploymentServiceService) { }

  addVehicleDeploymentPlan(): void {
    this.vehicleDeploymentService.vehicleDeploymentPlannings.push({...this.newVehicleDeploymentPlan});
    // Reset newVehicleDeploymentPlan for next entry
    this.newVehicleDeploymentPlan = {
      id: null,
      // Reset other fields if needed
    };
  }

  saveVehicleDeploymentService(): void {
    this.vehicleDeploymentServiceService.createVehicleDeploymentService(this.vehicleDeploymentService).subscribe(() => {
      console.log('Vehicle deployment service created successfully!');
      // Reset form or navigate to another page
    });
  }
}
