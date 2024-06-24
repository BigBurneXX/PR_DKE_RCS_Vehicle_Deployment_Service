import {Component, Inject} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle
} from "@angular/material/dialog";
import {VehicleInputDto} from "../dtos/VehicleInput.dto";
import {NgForOf} from "@angular/common";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-vehicle-modal',
  standalone: true,
  imports: [
    MatDialogTitle,
    MatDialogContent,
    NgForOf,
    MatDialogActions,
    MatButton,
    MatDialogClose
  ],
  templateUrl: './vehicle-modal.component.html',
  styleUrl: './vehicle-modal.component.scss'
})
export class VehicleModalComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { vehicles: VehicleInputDto[] }) {}
}
