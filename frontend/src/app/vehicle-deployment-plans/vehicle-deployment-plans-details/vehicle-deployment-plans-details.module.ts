import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { VehicleDeploymentPlansDetailsComponent } from "./vehicle-deployment-plans-details.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Vehicle Deployment Plan",
      urls: [{ title: "Vehicle Deployment Plan", url: "/vehicle-deployment-plans/details" }, { title: "Vehicle Deployment Plan" }],
    },
    component: VehicleDeploymentPlansDetailsComponent,
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
export class VehicleDeploymentPlansDetailsModule {}
