import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import { TripSheetsDetailsComponent } from "./trip-sheets-details.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "TripSheet",
      urls: [{ title: "TripSheet", url: "/trip-sheets/details" }, { title: "TripSheet" }],
    },
    component: TripSheetsDetailsComponent,
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
export class TripSheetsDetailsModule {}
