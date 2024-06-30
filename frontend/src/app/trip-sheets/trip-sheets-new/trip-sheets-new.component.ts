import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

import { TripSheetService } from "../trip-sheet.service";
import { VehicleDeploymentPlanService } from "../../vehicle-deployment-plans/vehicle-deployment-plan.service";
import { VehicleDeploymentPlanOutputDto } from "../../dtos/VehicleDeploymentPlanOutput.dto";
import { PersonOutputDto } from "../../dtos/PersonOutput.dto";


@Component({
  selector: 'app-new-trip-sheet',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './trip-sheets-new.component.html',
  styleUrls: ['./trip-sheets-new.component.scss']
})
export class TripSheetsNewComponent implements OnInit {
  vehicleDeploymentPlans: VehicleDeploymentPlanOutputDto[] = [];
  selectedPlanId: number | undefined;
  plan: VehicleDeploymentPlanOutputDto | undefined;
  persons: PersonOutputDto[] = [];
  planError: boolean = false;
  selectedPersons: PersonOutputDto[] = [];

  constructor(private tripSheetService: TripSheetService,
              private vehicleDeploymentPlanService: VehicleDeploymentPlanService,
              private router: Router) { }

  ngOnInit() {
    this.loadAllPlans();
  }

  loadAllPlans() {
    this.vehicleDeploymentPlanService.getVehicleDeploymentPlans().subscribe({
      next: (data) => {
        this.vehicleDeploymentPlans = data;
      },
      error: () => {
        this.planError = true;
      }
    });
  }

  loadPlan(id: number){
    this.vehicleDeploymentPlanService.getVehicleDeploymentPlanById(id).subscribe({
      next: (data) => {
        this.plan = data;
        this.persons = this.plan.persons;
      },
      error: () => {
        this.planError = true;
      }
    });
  }

  onPlanChange(event: Event) {
    const target = event.target as HTMLSelectElement;
    const planId = Number(target.value);
    this.loadPlan(planId);
  }

  saveTripSheet() {
    this.selectedPersons = this.persons.filter(person => person.selected);
    this.tripSheetService.postTripSheet(this.plan?.id, this.selectedPersons).subscribe(response => {
      console.log('Trip Sheet saved successfully:', response);
    });
    this.router.navigate(['/trip-sheets/all']);
  }
}
