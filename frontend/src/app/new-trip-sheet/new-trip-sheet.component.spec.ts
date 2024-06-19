import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripSheetComponent } from './new-trip-sheet.component';

describe('VehicleDeploymentPlanningComponent', () => {
  let component: TripSheetComponent;
  let fixture: ComponentFixture<TripSheetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripSheetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
