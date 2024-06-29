import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes, RouterModule } from "@angular/router";

import {TripSheetComponent} from "./trip-sheet.component";

const routes: Routes = [
  {
    path: "",
    data: {
      title: "TripSheet",
      urls: [{ title: "TripSheet", url: "/trip-sheets" }, { title: "TripSheet" }],
    },
    component: TripSheetComponent,
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
export class TripSheetModule {}
