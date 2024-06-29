import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {NewTripSheetComponent } from "./new-trip-sheet.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "Create new trip sheet",
      urls: [{ title: "New Trip Sheet", url: "/new-trip-sheet" }, { title: "New Trip Sheet" }],
    },
    component: NewTripSheetComponent,
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
export class NewTripSheetModule {}
