import { HttpHeaders, HttpErrorResponse, HttpClient} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { TripSheet } from '../trip-sheet/TripSheet';
import {TripSheetInputDto} from "../new-trip-sheet/TripSheetInput.dto";

@Injectable({
  providedIn: 'root'
})
export class TripSheetService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getTripSheets(): Observable<TripSheet[]> {
    return this.http.get<TripSheet[]>('http://localhost:8082/trip-sheets/').pipe(
      tap(_ => console.log('Trip sheets fetched')),
      catchError((error: HttpErrorResponse) => {
        return throwError(error.message || 'Server error');
      })
    );
  }

  postTripSheet(tripSheet: TripSheetInputDto): Observable<TripSheetInputDto> {
    return this.http.post<TripSheetInputDto>('http://localhost:8082/trip-sheets/', tripSheet, this.httpOptions).pipe(
      tap(_ => console.log('Trip sheet added')),
      catchError((error: HttpErrorResponse) => {
        return throwError(error.message || 'Server error');
      })
    );
  }
}
