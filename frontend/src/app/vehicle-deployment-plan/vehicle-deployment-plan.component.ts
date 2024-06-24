import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {VehicleDeploymentPlanService} from "../services/vehicle-deployment-plan.service";
import {VehicleDeploymentPlanOutputDto} from "../dtos/VehicleDeploymentPlanOutput.dto";

@Component({
  selector: 'app-vehicle-deployment-plan',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf
    ],
  templateUrl: './vehicle-deployment-plan.component.html',
  styleUrls: ['./vehicle-deployment-plan.component.scss']
})

export class VehicleDeploymentPlanComponent implements OnInit {
    plans: VehicleDeploymentPlanOutputDto[] = [];

    constructor(private vehicleDeploymentPlanService: VehicleDeploymentPlanService) {
    }

    ngOnInit() {
        this.loadPlans();
    }

    loadPlans() {
        this.vehicleDeploymentPlanService.getVehicleDeploymentPlans().subscribe(plans => {
            this.plans = plans;
        });
    }
}
