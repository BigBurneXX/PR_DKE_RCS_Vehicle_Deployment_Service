import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {VehicleDeploymentPlanService} from "../services/VehicleDeploymentPlanService";

@Component({
  selector: 'app-vehicle-deployment-plan',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf
    ],
  templateUrl: './vehicle-deployment-plan.component.html',
  styleUrls: ['./vehicle-deployment-plan.component.css']
})

export class VehicleDeploymentPlanComponent implements OnInit {
    vehicleDeploymentPlans: any[] = [];

    constructor(private vehicleDeploymentPlanService: VehicleDeploymentPlanService) {
    }

    ngOnInit() {
        this.getVehicleDeploymentPlans();
    }

    getVehicleDeploymentPlans(): void {
        this.vehicleDeploymentPlanService.getVehicleDeploymentPlans().subscribe(vehicleDeploymentPlans => {
            this.vehicleDeploymentPlans = vehicleDeploymentPlans;
        });
    }
}
