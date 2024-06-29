import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { NgForOf } from "@angular/common";
import { MatDialog } from "@angular/material/dialog";

import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { VehicleDeploymentPlanningService } from "../../vehicle-deployment-plannings/vehicle-deployment-planning.service";
import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";
import { VehicleDeploymentPlanOutputDto } from "../../dtos/VehicleDeploymentPlanOutput.dto";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";
import {LocationDto} from "../../dtos/Location.dto";

@Component({
  selector: 'app-vehicle-deployment-planning-details',
  standalone: true,
  imports: [
    NgForOf,
    CustomDatePipe
  ],
  templateUrl: './vehicle-deployment-plans-planning.component.html',
  styleUrl: './vehicle-deployment-plans-planning.component.scss'
})
export class VehicleDeploymentPlansPlanningComponent implements OnInit {
  plans: VehicleDeploymentPlanOutputDto[] = [];
  planningId: number | undefined;

  constructor(private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService,
              private dialog: MatDialog, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.planningId = Number(this.route.snapshot.paramMap.get('id')!)
    this.loadPlans(this.planningId);
  }

  loadPlans(planningId: number): void {
    this.vehicleDeploymentPlanningService.getVehicleDeploymentPlanningById(planningId).subscribe(planning => {
      this.plans = planning.plans;
    });
  }

  openPersonModal(persons: PersonOutputDto[]): void {
    this.dialog.open(PersonModalComponent, {
      data: { persons }
    });
  }

  viewOnMap(locations: LocationDto[]): void {
    const coordinates = locations.map(location => [location.longitude, location.latitude]);
    this.router.navigate(['/route-map'], { queryParams: { coordinates: JSON.stringify(coordinates) } });
  }

  navigateToDetails(planId: number) {
    this.router.navigate([`/vehicle-deployment-plans/details/${planId}`]);
  }

  navigateToTripSheets(planId: number) {
    this.router.navigate([`/trip-sheets/plan/${planId}`]);
  }
  navigateToPlanning(planningId: number) {
    this.router.navigate([`/vehicle-deployment-plannings/details/${planningId}`]);
  }
}
