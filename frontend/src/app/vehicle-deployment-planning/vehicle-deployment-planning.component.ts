import {Component, OnInit} from '@angular/core';
import { FormsModule } from "@angular/forms";
import { NgForOf, NgIf } from "@angular/common";
import { VehicleDeploymentPlanningService } from "../services/vehicle-deployment-planning.service";
import {VehicleDeploymentPlanningInputDto} from "../component/dtos/VehicleDeploymentPlanningInput.dto";
import {PersonInputDto} from "../component/dtos/PersonInput.dto";
import {VehicleInputDto} from "../component/dtos/VehicleInput.dto";

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
export class VehicleDeploymentPlanningComponent implements OnInit {
  people: PersonInputDto[] = [];
  vehicles: VehicleInputDto[] = [];
  peopleError: boolean = false;
  vehiclesError: boolean = false;
  vehicleDeploymentPlanning: VehicleDeploymentPlanningInputDto = new VehicleDeploymentPlanningInputDto();

  constructor(private vehicleDeploymentPlanningService: VehicleDeploymentPlanningService) { }

  ngOnInit() {
    this.loadPeople();
    this.loadVehicles();
  }

  loadPeople() {
    this.vehicleDeploymentPlanningService.getPeople().subscribe(
        data => {
            this.people = data;
        },
        error => {
          this.peopleError = true;
        });
  }

  loadVehicles(){
    this.vehicleDeploymentPlanningService.getVehicles().subscribe(
        data => {
            this.vehicles = data;
        },
        error => {
          this.vehiclesError = true;
        });
  }

  savePlanning() {
    this.vehicleDeploymentPlanning.persons = this.people.filter(person => person.selected);
    this.vehicleDeploymentPlanning.vehicles = this.vehicles.filter(vehicle => vehicle.selected);
    this.vehicleDeploymentPlanningService.postVehicleDeploymentPlanning(this.vehicleDeploymentPlanning).subscribe(response => {
      console.log('Vehicle Deployment Planning saved successfully:', response);
    });
  }
}
