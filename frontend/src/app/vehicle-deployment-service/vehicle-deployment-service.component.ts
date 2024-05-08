import { Component } from '@angular/core';
import { VehicleDeploymentService } from './VehicleDeploymentService';
import { VehicleDeploymentServiceService } from "./VehicleDeploymentServiceService";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-vehicle-deployment-service',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './vehicle-deployment-service.component.html',
  styleUrls: ['./vehicle-deployment-service.component.css']
})

export class VehicleDeploymentServiceComponent {
  vehicleDeploymentService: VehicleDeploymentService = {
    id: null,
    serviceName: '',
    description: '',
    vehicleDeploymentPlannings: []
  };

  constructor(private vehicleDeploymentServiceService: VehicleDeploymentServiceService) {
  }

  saveVehicleDeploymentService(): void {
    this.vehicleDeploymentServiceService.addVehicleDeploymentService(this.vehicleDeploymentService).subscribe(() => {
      console.log('Vehicle deployment service created successfully!');
      // Reset form or navigate to another page
    });
  }
}
