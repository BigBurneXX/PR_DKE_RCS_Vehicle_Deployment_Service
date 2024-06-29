import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { VehicleDeploymentPlanningsPlansComponent } from "./vehicle-deployment-plannings-plans.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Show a specific Vehicle Deployment Planning",
      urls: [{ title: "Vehicle Deployment Planning", url: "/vehicle-deployment-plannings/plans" }, { title: "Vehicle Deployment Planning" }],
    },
    component: VehicleDeploymentPlanningsPlansComponent,
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
export class VehicleDeploymentPlanningsPlansModule {}
