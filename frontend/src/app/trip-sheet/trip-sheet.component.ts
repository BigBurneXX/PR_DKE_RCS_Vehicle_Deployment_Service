import { Component } from '@angular/core';
import { TripSheetService } from "./TripSheetService";
import { TripSheet } from "./TripSheet";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-trip-sheet',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './trip-sheet.component.html',
  styleUrl: './trip-sheet.component.css'
})
export class TripSheetComponent {
  newTripSheet: any = TripSheet;
  constructor(private tripSheetService: TripSheetService) {
  }

  saveTripSheet(): void {
    this.tripSheetService.addTripSheet(this.newTripSheet).subscribe(() => {
      console.log('Vehicle deployment service created successfully!', this.tripSheetService.getTripSheets());
    });
  }
}
