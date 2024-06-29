import { Component, OnInit } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { VehicleDeploymentPlanService } from "../vehicle-deployment-plan.service";
import { VehicleDeploymentPlanOutputDto } from "../../dtos/VehicleDeploymentPlanOutput.dto";
import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { LocationDto } from "../../dtos/Location.dto";
import { Router } from "@angular/router";
import { MatDialog } from "@angular/material/dialog";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";
import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";

@Component({
  selector: 'app-vehicle-deployment-plan',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf,
        CustomDatePipe
    ],
  templateUrl: './vehicle-deployment-plan.component.html',
  styleUrls: ['./vehicle-deployment-plan.component.scss']
})

export class VehicleDeploymentPlanComponent implements OnInit {
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
}
