import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {VehicleDeploymentPlanningComponent} from "./vehicle-deployment-planning.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "VehicleDto Deployment Planning",
      urls: [{ title: "VehicleDto Deployment Planning", url: "/vehicle-deployment-plannings" }, { title: "VehicleDto Deployment Planning" }],
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
