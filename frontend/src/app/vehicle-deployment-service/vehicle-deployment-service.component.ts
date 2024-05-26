import { Component } from '@angular/core';
import { VehicleDeploymentService } from './VehicleDeploymentService';
import { VehicleDeploymentServiceService } from "./VehicleDeploymentServiceService";
import { FormsModule } from "@angular/forms";
import { NgFor } from "@angular/common";

@Component({
  selector: 'app-vehicle-deployment-service',
  standalone: true,
  imports: [
    NgFor, FormsModule
  ],
  templateUrl: './vehicle-deployment-service.component.html',
  styleUrls: ['./vehicle-deployment-service.component.css']
})

export class VehicleDeploymentServiceComponent {
  newVehicleDeploymentService: VehicleDeploymentService = {
    id: null,
    serviceName: '',
    description: '',
    vehicleDeploymentPlannings: []
  };

  constructor(private vehicleDeploymentServiceService: VehicleDeploymentServiceService) {
  }

  saveVehicleDeploymentService(): void {
    this.vehicleDeploymentServiceService.addVehicleDeploymentService(this.newVehicleDeploymentService).subscribe(() => {
      console.log('Vehicle deployment service created successfully!');
    });
    console.log(this.vehicleDeploymentServiceService.getVehicleDeploymentServices());
  }
}
