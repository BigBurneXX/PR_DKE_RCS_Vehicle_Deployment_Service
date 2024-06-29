import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { NgForOf } from "@angular/common";
import { MatDialog } from "@angular/material/dialog";

import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { VehicleDeploymentPlanningService } from "../vehicle-deployment-planning.service";
import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";
import { VehicleDeploymentPlanOutputDto } from "../../dtos/VehicleDeploymentPlanOutput.dto";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";

@Component({
  selector: 'app-vehicle-deployment-planning-details',
  standalone: true,
  imports: [
    NgForOf,
    CustomDatePipe
  ],
  templateUrl: './vehicle-deployment-plannings-plans.component.html',
  styleUrl: './vehicle-deployment-plannings-plans.component.scss'
})
export class VehicleDeploymentPlanningsPlansComponent implements OnInit {
  planningId: number | undefined;
  plans: VehicleDeploymentPlanOutputDto[] = [];

  constructor(private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService,
              private dialog: MatDialog, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.planningId = Number(this.route.snapshot.paramMap.get('id')!);
    this.getPlans();
  }

  getPlans(): void {
    this.vehicleDeploymentPlanningService.getVehicleDeploymentPlanning(this.planningId).subscribe(planning => {
      this.plans = planning.plans;
    });
  }

  openPersonModal(persons: PersonOutputDto[]): void {
    this.dialog.open(PersonModalComponent, {
      data: { persons }
    });
  }

  navigateToTripSheets(planId: number) {
    this.router.navigate([`/trip-sheets/${planId}`])
  }
}
