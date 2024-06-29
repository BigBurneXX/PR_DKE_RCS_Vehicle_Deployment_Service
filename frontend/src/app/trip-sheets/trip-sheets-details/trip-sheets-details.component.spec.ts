import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripSheetsDetailsComponent } from './trip-sheets-details.component';

describe('TripSheetDetailsComponent', () => {
  let component: TripSheetsDetailsComponent;
  let fixture: ComponentFixture<TripSheetsDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripSheetsDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripSheetsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
