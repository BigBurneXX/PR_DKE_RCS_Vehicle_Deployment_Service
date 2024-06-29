import { NgForOf } from "@angular/common";
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { CustomDatePipe } from "../../shared/CustomDatePipe";
import { TripSheetService } from "../trip-sheet.service";

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

  constructor(private route: ActivatedRoute, private tripSheetService: TripSheetService) {}

  ngOnInit(): void {
    this.planId = Number(this.route.snapshot.paramMap.get('id')!);
    this.getTripSheets();
  }

  getTripSheets(): void {
    this.tripSheetService.getTripSheetByPlanId(this.planId).subscribe(tripSheets => {
      this.tripSheets = tripSheets;
    });
  }
}
