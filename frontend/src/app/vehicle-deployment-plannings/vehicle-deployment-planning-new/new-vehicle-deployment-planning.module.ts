import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {NewVehicleDeploymentPlanningComponent} from "./new-vehicle-deployment-planning.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Create new Vehicle Deployment Planning",
      urls: [{ title: "Vehicle Deployment Planning", url: "/new-vehicle-deployment-plannings" }, { title: "Vehicle Deployment Planning" }],
    },
    component: NewVehicleDeploymentPlanningComponent,
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
export class NewVehicleDeploymentPlanningModule {}
