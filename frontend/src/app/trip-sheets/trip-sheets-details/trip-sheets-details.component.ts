import { Component, OnInit } from '@angular/core';
import { Location, NgForOf, NgIf } from "@angular/common";
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { catchError, of } from "rxjs";

import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";
import { TripSheetService } from "../trip-sheet.service";
import { TripSheetOutputDto } from "../../dtos/TripSheetOutput.dto";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";
import { LocationDto } from "../../dtos/Location.dto";

@Component({
  selector: 'app-trip-sheet-details',
  standalone: true,
    imports: [
        CustomDatePipe,
        NgForOf,
        NgIf
    ],
  templateUrl: './trip-sheets-details.component.html',
  styleUrl: './trip-sheets-details.component.scss'
})
export class TripSheetsDetailsComponent implements OnInit {
    tripSheet: TripSheetOutputDto | undefined;

    constructor(private tripSheetService: TripSheetService,
                private dialog: MatDialog,
                private router: Router,
                private route: ActivatedRoute,
                private location: Location) {
    }

    ngOnInit() {
        this.loadTripSheet(Number(this.route.snapshot.paramMap.get('id')!));
    }

    loadTripSheet(id: number): void {
        this.tripSheetService.getTripSheetById(id).pipe(
            catchError(() => {
                alert(`Unable to find Trip Sheet with id ${id}`);
                this.location.back();
                return of(null); // Return an observable with null value to complete the stream
            })
        ).subscribe(tripSheet => {
            if (tripSheet) {
                this.tripSheet = tripSheet;
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

    navigateToPlan(planId: number) {
        this.router.navigate([`/vehicle-deployment-plans/details/${planId}`]);
    }
}
