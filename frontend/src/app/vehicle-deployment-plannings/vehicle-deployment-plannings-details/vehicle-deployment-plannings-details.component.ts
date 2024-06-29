import {Component, OnInit} from '@angular/core';
import {CustomDatePipe} from "../../shared/CustomDatePipe";
import {Location, NgForOf, NgIf} from "@angular/common";
import {VehicleDeploymentPlanningOutputDto} from "../../dtos/VehicleDeploymentPlanningOutput.dto";
import {VehicleDeploymentPlanningService} from "../vehicle-deployment-planning.service";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {PersonOutputDto} from "../../dtos/PersonOutput.dto";
import {PersonModalComponent} from "../../modals/person-modal/person-modal.component";
import {VehicleOutputDto} from "../../dtos/VehicleOutput.dto";
import {VehicleModalComponent} from "../../modals/vehicle-modal/vehicle-modal.component";
import {catchError, of} from "rxjs";

@Component({
  selector: 'app-vehicle-deployment-plannings-details',
  standalone: true,
    imports: [
        CustomDatePipe,
        NgForOf,
        NgIf
    ],
  templateUrl: './vehicle-deployment-plannings-details.component.html',
  styleUrl: './vehicle-deployment-plannings-details.component.scss'
})
export class VehicleDeploymentPlanningsDetailsComponent implements OnInit {
    planning: VehicleDeploymentPlanningOutputDto | undefined;
    constructor(private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService,
                private dialog: MatDialog,
                private router: Router,
                private route: ActivatedRoute,
                private location: Location) {}

    ngOnInit() {
        this.loadPlanning(Number(this.route.snapshot.paramMap.get('id')!));
    }

    loadPlanning(id: number) {
        this.vehicleDeploymentPlanningService.getVehicleDeploymentPlanningById(id).pipe(
            catchError(() => {
                alert(`Unable to find Plan with id ${id}`);
                this.location.back();
                return of(null); // Return an observable with null value to complete the stream
            })
        ).subscribe(planning => {
            if (planning) {
                this.planning = planning;
            } else {
                this.location.back();
            }
        });
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

    navigateToPlans(id: number): void {
        this.router.navigate([`/vehicle-deployment-plans/planning/${id}`]);
    }
}
