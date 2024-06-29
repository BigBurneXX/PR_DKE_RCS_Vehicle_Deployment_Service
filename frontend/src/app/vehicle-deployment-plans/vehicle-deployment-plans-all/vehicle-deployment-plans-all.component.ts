import { Component, OnInit } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { Router } from "@angular/router";
import { MatDialog } from "@angular/material/dialog";

import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";
import { VehicleDeploymentPlanService } from "../vehicle-deployment-plan.service";
import { VehicleDeploymentPlanOutputDto } from "../../dtos/VehicleDeploymentPlanOutput.dto";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";
import { LocationDto } from "../../dtos/Location.dto";

@Component({
  selector: 'app-vehicle-deployment-plan',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf,
    ],
  templateUrl: './vehicle-deployment-plans-all.component.html',
  styleUrls: ['./vehicle-deployment-plans-all.component.scss']
})

export class VehicleDeploymentPlansAllComponent implements OnInit {
    plans: VehicleDeploymentPlanOutputDto[] = [];

    constructor(private vehicleDeploymentPlanService: VehicleDeploymentPlanService,
                private dialog: MatDialog,
                private router: Router) {
    }

    ngOnInit() {
        this.loadPlans();
    }

    loadPlans() {
        this.vehicleDeploymentPlanService.getVehicleDeploymentPlans().subscribe(plans => {
            this.plans = plans;
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
