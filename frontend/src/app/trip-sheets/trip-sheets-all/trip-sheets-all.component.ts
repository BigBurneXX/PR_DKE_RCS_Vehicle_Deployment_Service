import { Component, OnInit } from '@angular/core';
import { NgForOf, NgIf } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { Router } from "@angular/router";

import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { ConfirmDialogModalComponent } from "../../modals/confirm-dialog-modal/confirm-dialog-modal.component";
import { PersonModalComponent } from "../../modals/person-modal/person-modal.component";
import { TripSheetService } from "../trip-sheet.service";
import { TripSheetOutputDto } from "../../dtos/TripSheetOutput.dto";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";
import { LocationDto } from "../../dtos/Location.dto";

@Component({
  standalone: true,
  templateUrl: './trip-sheets-all.component.html',
    imports: [
        NgIf,
        NgForOf,
        FormsModule,
        CustomDatePipe
    ],
  styleUrls: ['./trip-sheets-all.component.scss']
})
export class TripSheetsAllComponent implements OnInit {
  tripSheets: TripSheetOutputDto[] = [];

  constructor(private tripSheetService: TripSheetService, private dialog: MatDialog, private router: Router) {
  }

  ngOnInit() {
    this.loadTripSheets();
  }

  loadTripSheets(): void {
    this.tripSheetService.getTripSheets().subscribe(tripSheets => {
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

    confirmDelete(id: number) {
        const dialogRef = this.dialog.open(ConfirmDialogModalComponent, {
            data: {
                message: 'Are you sure you want to delete this trip sheet? This action cannot be undone.'
            }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === true) {
                this.deleteTripSheet(id);
            }
        });
    }

    deleteTripSheet(id: number) {
        this.tripSheetService.deleteTripSheet(id).subscribe(() => {
            this.tripSheets = this.tripSheets.filter(tripSheet => tripSheet.id !== id);
            console.log('Trip sheet deleted successfully');
        });
    }
}
