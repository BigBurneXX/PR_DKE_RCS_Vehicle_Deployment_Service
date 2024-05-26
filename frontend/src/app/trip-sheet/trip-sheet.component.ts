import { Component } from '@angular/core';
import { TripSheetService } from "./TripSheetService";
import { TripSheet } from "./TripSheet";
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";

@Component({
  standalone: true,
  templateUrl: './trip-sheet.component.html',
  imports: [
    NgIf,
    NgForOf,
    FormsModule
  ],
  styleUrls: ['./trip-sheet.component.css']
})
export class TripSheetComponent {
  newTripSheet: any = TripSheet;
  tripSheets: any[] = [];

  constructor(private tripSheetService: TripSheetService) {
  }

  saveTripSheet(): void {
    this.tripSheetService.addTripSheet(this.newTripSheet).subscribe(() => {
      console.log('Trip sheet created successfully!');
      this.getTripSheets();
    });
  }

  getTripSheets(): void {
    this.tripSheetService.getTripSheets().subscribe(tripSheets => {
      this.tripSheets = tripSheets;
    });
  }
}
