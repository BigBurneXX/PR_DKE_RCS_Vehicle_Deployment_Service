import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

import { VehicleDeploymentPlanningService } from "../vehicle-deployment-plannings/vehicle-deployment-planning.service";
import {VehicleDeploymentPlanOutputDto} from "../dtos/VehicleDeploymentPlanOutput.dto";

@Component({
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  currentPlans: any;
  plans: VehicleDeploymentPlanOutputDto[] = [];

  constructor(private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService,
              private router: Router) {}

  ngOnInit(): void {
    this.loadLatestPlan();
  }

  loadLatestPlan(): void {
    this.vehicleDeploymentPlanningService.getVehicleDeploymentPlannings().subscribe(plannings => {
      if (plannings && plannings.length > 0) {
        const latestPlanning = plannings.reduce((prev, curr) => {
          return new Date(prev.creationDate) > new Date(curr.creationDate) ? prev : curr;
        });

        this.currentPlans = latestPlanning.plans;

        // Pass the plans as query parameters to the route map component
        this.router.navigate([], {
          queryParams: { plans: JSON.stringify(this.currentPlans) },
          queryParamsHandling: 'merge'
        });
      }
    });
  }
}
