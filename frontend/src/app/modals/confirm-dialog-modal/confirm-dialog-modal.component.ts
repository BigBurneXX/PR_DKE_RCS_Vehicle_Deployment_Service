import {Component, Inject} from '@angular/core';
import { MatButton } from "@angular/material/button";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";

@Component({
  selector: 'app-confirm-dialog-modal',
  standalone: true,
  imports: [
    MatDialogActions,
    MatButton,
    MatDialogContent,
    MatDialogTitle
  ],
  templateUrl: './confirm-dialog-modal.component.html',
  styleUrl: './confirm-dialog-modal.component.scss'
})
export class ConfirmDialogModalComponent {
  constructor(
      public dialogRef: MatDialogRef<ConfirmDialogModalComponent>,
      @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onCancel(): void {
    this.dialogRef.close(false);
  }

  onConfirm(): void {
    this.dialogRef.close(true);
  }
}
