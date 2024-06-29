import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import { VehicleDeploymentPlanningsDetailsComponent } from "./vehicle-deployment-plannings-details.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Create new Vehicle Deployment Planning",
      urls: [{ title: "Vehicle Deployment Planning", url: "/vehicle-deployment-plannings/details" }, { title: "Vehicle Deployment Planning" }],
    },
    component: VehicleDeploymentPlanningsDetailsComponent,
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
export class VehicleDeploymentPlanningsDetailsModule {}
