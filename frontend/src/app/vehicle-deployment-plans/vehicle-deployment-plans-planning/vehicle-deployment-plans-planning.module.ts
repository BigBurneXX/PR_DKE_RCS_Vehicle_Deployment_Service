import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";
import { VehicleDeploymentPlansPlanningComponent } from "./vehicle-deployment-plans-planning.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Show a specific Vehicle Deployment Planning",
      urls: [{ title: "Vehicle Deployment Planning", url: "/vehicle-deployment-plans/planning" }, { title: "Vehicle Deployment Planning" }],
    },
    component: VehicleDeploymentPlansPlanningComponent,
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
export class VehicleDeploymentPlansPlanningModule {}
