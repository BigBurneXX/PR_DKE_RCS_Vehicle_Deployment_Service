import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {VehicleDeploymentPlanningComponent} from "./vehicle-deployment-planning.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Vehicle Deployment Planning",
      urls: [{ title: "Vehicle Deployment Planning", url: "/vehicle-deployment-plannings" }, { title: "Vehicle Deployment Planning" }],
    },
    component: VehicleDeploymentPlanningComponent,
  },
];

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule.forChild(routes),
  ],
  declarations: [
  ],
})
export class VehicleDeploymentPlanningModule {}
