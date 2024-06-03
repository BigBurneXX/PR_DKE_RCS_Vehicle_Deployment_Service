import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-vehicle-deployment-plan',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf
    ],
  templateUrl: './vehicle-deployment-plan.component.html',
  styleUrls: ['./vehicle-deployment-plan.component.css']
})
export class VehicleDeploymentPlanComponent {

}
