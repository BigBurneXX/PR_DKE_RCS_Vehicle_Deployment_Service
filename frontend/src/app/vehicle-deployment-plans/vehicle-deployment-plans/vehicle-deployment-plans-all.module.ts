import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {VehicleDeploymentPlansAllComponent} from "./vehicle-deployment-plans-all.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Vehicle Deployment Plan",
      urls: [{ title: "Vehicle Deployment Plan", url: "/vehicle-deployment-plans" }, { title: "Vehicle Deployment Plan" }],
    },
    component: VehicleDeploymentPlansAllComponent,
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
export class VehicleDeploymentPlansAllModule {}
