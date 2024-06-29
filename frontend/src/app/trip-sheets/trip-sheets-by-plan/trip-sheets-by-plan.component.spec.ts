import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripSheetsByPlanComponent } from './trip-sheets-by-plan.component';

describe('TripSheetsByPlanComponent', () => {
  let component: TripSheetsByPlanComponent;
  let fixture: ComponentFixture<TripSheetsByPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripSheetsByPlanComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripSheetsByPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
