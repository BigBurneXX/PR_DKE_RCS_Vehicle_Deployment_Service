import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripSheetsAllComponent } from './trip-sheets-all.component';

describe('TripSheetComponent', () => {
  let component: TripSheetsAllComponent;
  let fixture: ComponentFixture<TripSheetsAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripSheetsAllComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripSheetsAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
