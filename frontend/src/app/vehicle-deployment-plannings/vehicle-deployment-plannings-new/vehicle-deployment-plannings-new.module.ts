import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {VehicleDeploymentPlanningsNewComponent} from "./vehicle-deployment-plannings-new.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Create new Vehicle Deployment Planning",
      urls: [{ title: "Vehicle Deployment Planning", url: "/vehicle-deployment-plannings/new" }, { title: "Vehicle Deployment Planning" }],
    },
    component: VehicleDeploymentPlanningsNewComponent,
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
export class VehicleDeploymentPlanningsNewModule {}
