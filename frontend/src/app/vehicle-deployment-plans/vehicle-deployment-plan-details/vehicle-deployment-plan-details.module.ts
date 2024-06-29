import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { VehicleDeploymentPlanDetailsComponent } from "./vehicle-deployment-plan-details.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Vehicle Deployment Plan",
      urls: [{ title: "Vehicle Deployment Plan", url: "/vehicle-deployment-plans/details" }, { title: "Vehicle Deployment Plan" }],
    },
    component: VehicleDeploymentPlanDetailsComponent,
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
export class VehicleDeploymentPlanDetailsModule {}
