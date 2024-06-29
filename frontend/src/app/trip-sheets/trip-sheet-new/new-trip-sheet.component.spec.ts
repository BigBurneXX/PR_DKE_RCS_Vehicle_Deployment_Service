import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTripSheetComponent } from './new-trip-sheet.component';

describe('VehicleDeploymentPlanningComponent', () => {
  let component: NewTripSheetComponent;
  let fixture: ComponentFixture<NewTripSheetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewTripSheetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewTripSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
