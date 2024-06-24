import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {TripSheetOutputDto} from "../dtos/TripSheetOutput.dto";

@Injectable({
  providedIn: 'root'
})
export class TripSheetServices {
  private backendUrl = 'http://localhost:8082/trip-sheets';

  constructor(private http: HttpClient) { }

  getTripSheets(): Observable<TripSheetOutputDto[]> {
    return this.http.get<TripSheetOutputDto[]>(this.backendUrl);
  }
/*
  postTripSheet(tripSheet: TripSheetInputDto): Observable<TripSheetInputDto> {
    return this.http.post<TripSheetInputDto>('http://localhost:8082/trip-sheets/', tripSheet, this.httpOptions).pipe(
      tap(_ => console.log('Trip sheet added')),
      catchError((error: HttpErrorResponse) => {
        return throwError(error.message || 'Server error');
      })
    );
  }*/
}
