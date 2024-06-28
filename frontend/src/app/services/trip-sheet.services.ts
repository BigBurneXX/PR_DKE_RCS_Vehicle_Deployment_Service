import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, tap, throwError} from 'rxjs';
import {Injectable} from '@angular/core';
import {TripSheetOutputDto} from "../dtos/TripSheetOutput.dto";
import {PersonOutputDto} from "../dtos/PersonOutput.dto";

@Injectable({
  providedIn: 'root'
})
export class TripSheetServices {
  private backendUrl = 'http://localhost:8082/trip-sheets';

  constructor(private http: HttpClient) { }

  getTripSheets(): Observable<TripSheetOutputDto[]> {
    return this.http.get<TripSheetOutputDto[]>(this.backendUrl);
  }

  postTripSheet(vehicleDeploymentPlanId: number | undefined, selectedPersons: PersonOutputDto[]): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/vehicleDeploymentPlan/${vehicleDeploymentPlanId}`, selectedPersons).pipe(
      tap(_ => console.log('Trip sheet added')),
      catchError((error: HttpErrorResponse) => {
        return throwError(error.message || 'Server error');
      })
    );
  }
}
