import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {TripSheetServices} from "../services/trip-sheet.services";
import {LocationDto} from "../dtos/Location.dto";
import {VehicleDeploymentPlanService} from "../services/vehicle-deployment-plan.service";
import {VehicleDeploymentPlanOutputDto} from "../dtos/VehicleDeploymentPlanOutput.dto";
import {PersonInputDto} from "../dtos/PersonInput.dto";

@Component({
  selector: 'app-new-trip-sheet',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './new-trip-sheet.component.html',
  styleUrls: ['./new-trip-sheet.component.scss']
})
export class NewTripSheetComponent implements OnInit{
  plan: VehicleDeploymentPlanOutputDto | undefined;
  persons: PersonInputDto[] = [];
  planError: boolean = false;
  locations: LocationDto[] = []

  constructor(private tripSheetService: TripSheetServices, private vehicleDeploymentPlanService: VehicleDeploymentPlanService) { }

  ngOnInit() {
    this.loadPlan(1);
  }

  loadPlan(id: number){
    this.vehicleDeploymentPlanService.getVehicleDeploymentPlanById(id).subscribe({
      next: (data) => {
        this.plan = data;
        this.vehicleDeploymentPlanService.getPeopleName(this.plan.persons).subscribe(persons => {
          this.persons = persons;
        });
      },
      error: () => {
        this.planError = true;
      }
    });
  }

  saveTripSheet() {
    /*
    this.tripSheet.vehicleDeploymentPlan = this.vehicleDeploymentPlan
    this.tripSheet.locations = this.locations.filter(location => location.selected);
    this.tripSheetService.postTripSheet(this.tripSheet).subscribe(response => {
      console.log('Trip Sheet saved successfully:', response);
    })*/
    console.log("Something");
  }
}
