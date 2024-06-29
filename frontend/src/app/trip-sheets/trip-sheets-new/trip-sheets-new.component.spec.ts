import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripSheetsNewComponent } from './trip-sheets-new.component';

describe('VehicleDeploymentPlanningComponent', () => {
  let component: TripSheetsNewComponent;
  let fixture: ComponentFixture<TripSheetsNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripSheetsNewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripSheetsNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
