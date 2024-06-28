import { Component, OnInit } from '@angular/core';
import { TripSheetServices } from "../services/trip-sheet.services";
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { TripSheetOutputDto } from "../dtos/TripSheetOutput.dto";
import { CustomDatePipe } from "../shared/CustomDatePipe";
import { PersonOutputDto } from "../dtos/PersonOutput.dto";
import { PersonModalComponent } from "../person-modal/person-modal.component";
import { LocationDto } from "../dtos/Location.dto";
import { MatDialog } from "@angular/material/dialog";
import { Router } from "@angular/router";

@Component({
  standalone: true,
  templateUrl: './trip-sheet.component.html',
    imports: [
        NgIf,
        NgForOf,
        FormsModule,
        CustomDatePipe
    ],
  styleUrls: ['./trip-sheet.component.scss']
})
export class TripSheetComponent implements OnInit {
  tripSheets: TripSheetOutputDto[] = [];

  constructor(private tripSheetService: TripSheetServices, private dialog: MatDialog, private router: Router) {
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
}
