import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {TripSheetsNewComponent } from "./trip-sheets-new.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Create new trip sheet",
      urls: [{ title: "New Trip Sheet", url: "/trip-sheets/new" }, { title: "New Trip Sheet" }],
    },
    component: TripSheetsNewComponent,
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
export class TripSheetNewModule {}
