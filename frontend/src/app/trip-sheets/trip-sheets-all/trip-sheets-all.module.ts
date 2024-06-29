import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {TripSheetsAllComponent} from "./trip-sheets-all.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "TripSheet",
      urls: [{ title: "TripSheet", url: "/trip-sheets/all" }, { title: "TripSheet" }],
    },
    component: TripSheetsAllComponent,
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
export class TripSheetsAllModule {}
