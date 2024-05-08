import { Routes } from '@angular/router';
import {VehicleDeploymentServiceComponent} from "./vehicle-deployment-service/vehicle-deployment-service.component";
import {VehicleDeploymentPlanningComponent} from "./vehicle-deployment-planning/vehicle-deployment-planning.component";
import {VehicleDeploymentPlanComponent} from "./vehicle-deployment-plan/vehicle-deployment-plan.component";
import {TripSheetComponent} from "./trip-sheet/trip-sheet.component";
import {MapComponent} from "./map/map.component";

export const routes: Routes = [
  { path: 'vehicle-deployment-services', title: 'Get to Vehicle Deployment Services!', component: VehicleDeploymentServiceComponent },
  { path: 'vehicle-deployment-plannings', title: 'Get to Vehicle Deployment Plannings!', component: VehicleDeploymentPlanningComponent },
  { path: 'vehicle-deployment-plans', title: 'Get to Vehicle Deployment Plans!', component: VehicleDeploymentPlanComponent },
  { path: 'trip-sheets', title: 'Get to Vehicle Deployment TripSheets!', component: TripSheetComponent },
  { path: 'map', title: 'Show Open Route Service!', component: MapComponent },
];
