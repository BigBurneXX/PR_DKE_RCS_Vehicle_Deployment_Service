import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import { TripSheetsByPlanComponent } from "./trip-sheets-by-plan.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "TripSheet",
      urls: [{ title: "TripSheet", url: "/trip-sheets/details" }, { title: "TripSheet" }],
    },
    component: TripSheetsByPlanComponent,
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
export class TripSheetsByPlanModule {}
