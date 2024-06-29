import { NgForOf } from "@angular/common";
import { Component, OnInit } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";

import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";
import { TripSheetService } from "../trip-sheet.service";
import { PersonOutputDto} from "../../dtos/PersonOutput.dto";
import { LocationDto } from "../../dtos/Location.dto";


@Component({
  selector: 'app-trip-sheets-by-plan',
  standalone: true,
  imports: [
    CustomDatePipe,
    NgForOf
  ],
  templateUrl: './trip-sheets-by-plan.component.html',
  styleUrl: './trip-sheets-by-plan.component.scss'
})
export class TripSheetsByPlanComponent implements OnInit {
  planId: number | undefined;
  tripSheets: any[] = [];

  constructor(private tripSheetService: TripSheetService,
              private router: Router,
              private route: ActivatedRoute,
              private dialog: MatDialog) {}

  ngOnInit(): void {
    this.planId = Number(this.route.snapshot.paramMap.get('id')!);
    this.getTripSheets();
  }

  getTripSheets(): void {
    this.tripSheetService.getTripSheetsByPlanId(this.planId).subscribe(tripSheets => {
      this.tripSheets = tripSheets;
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

  navigateToPlan(planId: number) {
    this.router.navigate([`/vehicle-deployment-plans/details/${planId}`]);
  }

  navigateToDetails(id: number) {
    this.router.navigate([`/trip-sheets/details/${id}`]);
  }
}
