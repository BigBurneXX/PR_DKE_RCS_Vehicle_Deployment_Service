import { AsyncPipe, Location, NgForOf, NgIf } from "@angular/common";
import { Component, OnInit } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { catchError, of } from "rxjs";

import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { PersonModalComponent} from "../../modals/person-modal/person-modal.component";
import { VehicleDeploymentPlanService } from "../vehicle-deployment-plan.service";
import { VehicleDeploymentPlanOutputDto } from "../../dtos/VehicleDeploymentPlanOutput.dto";
import { PersonOutputDto} from "../../dtos/PersonOutput.dto";
import { LocationDto} from "../../dtos/Location.dto";

@Component({
  selector: 'app-vehicle-deployment-plan-details',
  standalone: true,
  imports: [
    CustomDatePipe,
    NgForOf,
    AsyncPipe,
    NgIf
  ],
  templateUrl: './vehicle-deployment-plans-details.component.html',
  styleUrl: './vehicle-deployment-plans-details.component.scss'
})
export class VehicleDeploymentPlansDetailsComponent implements OnInit {
  plan: VehicleDeploymentPlanOutputDto | undefined;
  
  constructor(private vehicleDeploymentPlanService: VehicleDeploymentPlanService,
              private dialog: MatDialog,
              private router: Router,
              private route: ActivatedRoute,
              private location: Location) {}

  ngOnInit() {
    this.loadPlan(Number(this.route.snapshot.paramMap.get('id')!));
  }

  loadPlan(id: number) {
    this.vehicleDeploymentPlanService.getVehicleDeploymentPlanById(id).pipe(
        catchError(() => {
          alert(`Unable to find Plan with id ${id}`);
          this.location.back();
          return of(null); // Return an observable with null value to complete the stream
        })
    ).subscribe(plan => {
      if (plan) {
        this.plan = plan;
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

  viewOnMap(locations: LocationDto[]): void {
    const coordinates = locations.map(location => [location.longitude, location.latitude]);
    this.router.navigate(['/route-map'], { queryParams: { coordinates: JSON.stringify(coordinates) } });
  }

  navigateToTripSheets() {
    if(this.plan)
      this.router.navigate([`/trip-sheets/${this.plan.id}`]);
  }

  navigateToPlanning() {
    if(this.plan)
      this.router.navigate([`/vehicle-deployment-plannings/${this.plan.planningId}`]);
  }
}
