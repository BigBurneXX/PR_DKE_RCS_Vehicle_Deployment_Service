import { Component, OnInit } from '@angular/core';
import { VehicleDeploymentPlanningService } from "../vehicle-deployment-planning.service";
import { VehicleDeploymentPlanningOutputDto } from "../../dtos/VehicleDeploymentPlanningOutput.dto";
import { NgForOf} from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { Router } from "@angular/router";

import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";
import { VehicleModalComponent } from "../../modals/vehicle-modal/vehicle-modal.component";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";
import { VehicleOutputDto } from "../../dtos/VehicleOutput.dto";
import { CustomDatePipe } from "../../shared/CustomDatePipe";
import {VehicleDeploymentPlanOutputDto} from "../../dtos/VehicleDeploymentPlanOutput.dto";

@Component({
  selector: 'app-vehicle-deployment-planning-list',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CustomDatePipe,
    NgForOf
  ],
  templateUrl: './vehicle-deployment-plannings-all.component.html',
  styleUrl: './vehicle-deployment-plannings-all.component.scss'
})
export class VehicleDeploymentPlanningsAllComponent implements OnInit {
  plannings: VehicleDeploymentPlanningOutputDto[] = [];
  constructor(private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService,
              private dialog: MatDialog,
              private router: Router) { }

  ngOnInit() {
    this.loadVehicleDeploymentPlannings();
  }

  loadVehicleDeploymentPlannings() {
    this.vehicleDeploymentPlanningService.getVehicleDeploymentPlannings().subscribe(plannings =>{
        this.plannings = plannings;
    })
  }

  openPersonModal(persons: PersonOutputDto[]): void {
    this.dialog.open(PersonModalComponent, {
      data: { persons }
    });
  }

  openVehicleModal(vehicles: VehicleOutputDto[]): void {
    this.dialog.open(VehicleModalComponent, {
      data: { vehicles }
    });
  }

  navigateToPlans(planningId: number): void {
    this.router.navigate([`/vehicle-deployment-plans/planning/${planningId}`]);
  }

  navigateToDetails(planningId: number) {
    this.router.navigate([`/vehicle-deployment-plannings/details/${planningId}`]);
  }

  viewOnMap(plans: VehicleDeploymentPlanOutputDto[]) {
    this.router.navigate(['/route-map'], { queryParams: { plans: JSON.stringify(plans) } });
  }
}
