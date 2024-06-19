import {Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { VehicleDeploymentPlanDto } from "./VehicleDeploymentPlan.dto";
import { TripSheetInputDto } from "./TripSheetInput.dto";
import {TripSheetService} from "../services/TripSheetService";
import {LocationDto} from "./Location.dto";

@Component({
  selector: 'app-new-trip-sheet',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './new-trip-sheet.component.html',
  styleUrls: ['./new-trip-sheet.component.css']
})
export class NewTripSheetComponent {
  vehicleDeploymentPlan: any = VehicleDeploymentPlanDto;
  vehicleDeploymentPlanError: boolean = false;
  locations: LocationDto[] = []
  tripSheet: any = TripSheetInputDto;

  constructor(private tripSheetService: TripSheetService) { }

  saveTripSheet() {
    this.tripSheet.vehicleDeploymentPlan = this.vehicleDeploymentPlan
    this.tripSheet.locations = this.locations.filter(location => location.selected);
    this.tripSheetService.postTripSheet(this.tripSheet).subscribe(response => {
      console.log('Trip Sheet saved successfully:', response);
    })
  }
}
