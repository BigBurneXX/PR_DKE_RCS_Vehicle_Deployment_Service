import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripSheetDetailsComponent } from './trip-sheet-details.component';

describe('TripSheetDetailsComponent', () => {
  let component: TripSheetDetailsComponent;
  let fixture: ComponentFixture<TripSheetDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripSheetDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripSheetDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
