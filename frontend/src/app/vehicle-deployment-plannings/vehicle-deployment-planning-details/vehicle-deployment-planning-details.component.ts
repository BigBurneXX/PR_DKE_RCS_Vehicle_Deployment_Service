import { Component } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { VehicleDeploymentPlanningService } from "../vehicle-deployment-planning.service";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-vehicle-deployment-planning-details',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './vehicle-deployment-planning-details.component.html',
  styleUrl: './vehicle-deployment-planning-details.component.scss'
})
export class VehicleDeploymentPlanningDetailsComponent {
  planningId: number | undefined;
  plans: any[] = [];

  constructor(private route: ActivatedRoute, private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService) {}

  ngOnInit(): void {
    this.planningId = Number(this.route.snapshot.paramMap.get('id')!);
    this.getPlans();
  }

  getPlans(): void {
    this.vehicleDeploymentPlanningService.getVehicleDeploymentPlanning(this.planningId).subscribe(planning => {
      this.plans = planning.plans;
    });
  }

  navigateToTripSheets(id: number) {
    //TODO needs implementation
  }
}
