import { Routes } from '@angular/router';
import {VehicleDeploymentServiceComponent} from "./vehicle-deployment-service/vehicle-deployment-service.component";
import {VehicleDeploymentPlanningComponent} from "./vehicle-deployment-planning/vehicle-deployment-planning.component";
import {VehicleDeploymentPlanComponent} from "./vehicle-deployment-plan/vehicle-deployment-plan.component";
import {TripSheetComponent} from "./trip-sheet/trip-sheet.component";

export const routes: Routes = [
  { path: '', title: 'Get to Vehicle Deployment Services!', component: VehicleDeploymentServiceComponent },
  { path: '', title: 'Get to Vehicle Deployment Plannings!', component: VehicleDeploymentPlanningComponent },
  { path: '', title: 'Get to Vehicle Deployment Plans!', component: VehicleDeploymentPlanComponent },
  { path: '', title: 'Get to Vehicle Deployment TripSheets!', component: TripSheetComponent },
];
