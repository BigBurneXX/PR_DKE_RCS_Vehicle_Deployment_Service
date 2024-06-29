import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { TripSheetOutputDto } from "../dtos/TripSheetOutput.dto";
import { PersonOutputDto } from "../dtos/PersonOutput.dto";

@Injectable({
  providedIn: 'root'
})
export class TripSheetService {
  private backendUrl = 'http://localhost:8082/trip-sheets';

  constructor(private http: HttpClient) { }

  getTripSheets(): Observable<TripSheetOutputDto[]> {
    return this.http.get<TripSheetOutputDto[]>(this.backendUrl);
  }

  getTripSheetById(id: number): Observable<TripSheetOutputDto> {
    return this.http.get<TripSheetOutputDto>(`${this.backendUrl}/${id}`);
  }

  getTripSheetsByPlanId(planId: number | undefined): Observable<TripSheetOutputDto[]> {
    return this.http.get<TripSheetOutputDto[]>(`${this.backendUrl}/vehicleDeploymentPlan/${planId}`);
  }

  postTripSheet(planId: number | undefined, selectedPersons: PersonOutputDto[]): Observable<TripSheetOutputDto> {
    return this.http.post<TripSheetOutputDto>(`${this.backendUrl}/vehicleDeploymentPlan/${planId}`, selectedPersons);
  }
}
