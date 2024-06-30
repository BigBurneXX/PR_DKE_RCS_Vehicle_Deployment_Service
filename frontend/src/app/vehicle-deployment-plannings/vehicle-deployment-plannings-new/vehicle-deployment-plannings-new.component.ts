import { Component, OnInit } from '@angular/core';
import { NgForOf, NgIf } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";

import { VehicleDeploymentPlanningService } from "../vehicle-deployment-planning.service";
import { VehicleDeploymentPlanningInputDto } from "../../dtos/VehicleDeploymentPlanningInput.dto";
import { PersonInputDto } from "../../dtos/PersonInput.dto";
import { VehicleInputDto } from "../../dtos/VehicleInput.dto";

@Component({
  selector: 'app-vehicle-deployment-planning',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './vehicle-deployment-plannings-new.component.html',
  styleUrls: ['./vehicle-deployment-plannings-new.component.scss']
})
export class VehicleDeploymentPlanningsNewComponent implements OnInit {
  people: PersonInputDto[] = [];
  vehicles: VehicleInputDto[] = [];
  peopleError: boolean = false;
  vehiclesError: boolean = false;
  vehicleDeploymentPlanning: VehicleDeploymentPlanningInputDto = new VehicleDeploymentPlanningInputDto();

  constructor(private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService, private router: Router) { }

  ngOnInit() {
    this.loadPeople();
    this.loadVehicles();
  }

  loadPeople() {
    this.vehicleDeploymentPlanningService.getPeople().subscribe({
      next: (data) => {
        this.people = data;
      },
      error: () => {
          this.peopleError = true;
      }
    });
  }

  loadVehicles(){
    this.vehicleDeploymentPlanningService.getVehicles().subscribe({
        next: (data) => {
          this.vehicles = data;
        },
        error: () => {
          this.vehiclesError = true;
        }
    });
  }

  savePlanning(name: string) {
    this.vehicleDeploymentPlanning.name = name;
    this.vehicleDeploymentPlanning.persons = this.people.filter(person => person.selected);
    this.vehicleDeploymentPlanning.vehicles = this.vehicles.filter(vehicle => vehicle.selected);
    this.vehicleDeploymentPlanningService.getAddresses(this.vehicleDeploymentPlanning.persons).subscribe({
      next: (addresses) => {
        this.vehicleDeploymentPlanning.addresses = addresses
        this.vehicleDeploymentPlanningService.postVehicleDeploymentPlanning(this.vehicleDeploymentPlanning).subscribe(response => {
          console.log('Vehicle Deployment Planning saved successfully:', response);
        });
      }
    });
    console.log('Current URL:', this.router.url);
    console.log('Navigating to: /vehicle-deployment-planning');
    this.router.navigate(['/vehicle-deployment-plannings/all']);
  }
}
