import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {VehicleDeploymentServiceComponent} from "./vehicle-deployment-service.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Vehicle Deployment Service",
      urls: [{ title: "Vehicle Deployment Service", url: "/vehicle-deployment-services" }, { title: "Vehicle Deployment Service" }],
    },
    component: VehicleDeploymentServiceComponent,
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
export class VehicleDeploymentServiceModule {}
