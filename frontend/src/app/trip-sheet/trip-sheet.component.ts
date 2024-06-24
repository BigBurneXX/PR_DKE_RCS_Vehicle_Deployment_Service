import { Component, OnInit } from '@angular/core';
import { TripSheetServices } from "../services/trip-sheet.services";
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import {TripSheetOutputDto} from "../dtos/TripSheetOutput.dto";

@Component({
  standalone: true,
  templateUrl: './trip-sheet.component.html',
  imports: [
    NgIf,
    NgForOf,
    FormsModule
  ],
  styleUrls: ['./trip-sheet.component.scss']
})
export class TripSheetComponent implements OnInit {
  tripSheets: TripSheetOutputDto[] = [];

  constructor(private tripSheetService: TripSheetServices) {
  }

  ngOnInit() {
    this.loadTripSheets();
  }

  loadTripSheets(): void {
    this.tripSheetService.getTripSheets().subscribe(tripSheets => {
      this.tripSheets = tripSheets;
    });
  }
}
