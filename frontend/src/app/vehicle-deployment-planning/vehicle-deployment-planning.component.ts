import { Component } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { VehicleDeploymentPlanningService } from "../services/vehicle-deployment-planning.service";
import { PersonDto } from "./Person.dto";
import { VehicleDto } from "./Vehicle.dto";

@Component({
  selector: 'app-vehicle-deployment-planning',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './vehicle-deployment-planning.component.html',
  styleUrls: ['./vehicle-deployment-planning.component.css']
})
export class VehicleDeploymentPlanningComponent {
  people: PersonDto[] = [];
  vehicles: VehicleDto[] = [];

  constructor(private personService: VehicleDeploymentPlanningService) { }

  showPeople() {
    this.personService.getPeople().subscribe(data => {
      this.people = data;
    });
  }

  showVehicles(){
    this.personService.getVehicles().subscribe(data => {
      this.vehicles = data;
    });
  }
}
