import { Component, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle
} from '@angular/material/dialog';
import {MatButton} from "@angular/material/button";
import {NgForOf} from "@angular/common";
import {PersonOutputDto} from "../dtos/PersonOutput.dto";

@Component({
  selector: 'app-person-modal',
  standalone: true,
  imports: [
    MatButton,
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
    NgForOf
  ],
  templateUrl: './person-modal.component.html',
  styleUrl: './person-modal.component.scss'
})
export class PersonModalComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { persons: PersonOutputDto[] }) {}
}
