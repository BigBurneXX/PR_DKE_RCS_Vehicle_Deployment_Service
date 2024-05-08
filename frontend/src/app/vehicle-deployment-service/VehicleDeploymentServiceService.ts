import { HttpHeaders, HttpErrorResponse, HttpClient} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { VehicleDeploymentService } from './VehicleDeploymentService';

@Injectable({
  providedIn: 'root'
})
export class VehicleDeploymentServiceService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getVehicleDeploymentServices(): Observable<VehicleDeploymentService[]> {
    console.log("better yet");
    return this.http.get<VehicleDeploymentService[]>('http://localhost:8082/vehicle-deployment-services/').pipe(
      tap(_ => this.log('Vehicle deployment services fetched')),
      catchError((error: HttpErrorResponse) => {
        return throwError(error.message || 'Server error');
      })
    );
  }

  addVehicleDeploymentService(vehicleDeploymentService: VehicleDeploymentService): Observable<VehicleDeploymentService> {
    console.log("well");
    return this.http.post<VehicleDeploymentService>('http://localhost:8082/vehicle-deployment-services/', vehicleDeploymentService, this.httpOptions).pipe(
      tap(_ => this.log('Vehicle deployment service added')),
      catchError((error: HttpErrorResponse) => {
        return throwError(error.message || 'Server error');
      })
    );
  }

  private log(message: string) {
    console.log(message);
  }
}
