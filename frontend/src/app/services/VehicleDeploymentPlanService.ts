import { HttpHeaders, HttpErrorResponse, HttpClient} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { TripSheet } from '../trip-sheet/TripSheet';

@Injectable({
    providedIn: 'root'
})
export class VehicleDeploymentPlanService {
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    getVehicleDeploymentPlans(): Observable<TripSheet[]> {
        return this.http.get<TripSheet[]>('http://localhost:8082/vehicle-deployment-plans/').pipe(
            tap(_ => console.log('Vehicle deployment plans fetched')),
            catchError((error: HttpErrorResponse) => {
                return throwError(error.message || 'Server error');
            })
        );
    }
}
