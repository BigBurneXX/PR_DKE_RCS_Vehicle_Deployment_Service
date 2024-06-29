import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { VehicleDeploymentPlanOutputDto } from "../dtos/VehicleDeploymentPlanOutput.dto";

@Injectable({
    providedIn: 'root'
})
export class VehicleDeploymentPlanService {
    private backendUrl = 'http://localhost:8082/vehicle-plans';

    constructor(private http: HttpClient) { }

    getVehicleDeploymentPlans(): Observable<VehicleDeploymentPlanOutputDto[]> {
        return this.http.get<VehicleDeploymentPlanOutputDto[]>(this.backendUrl);
    }

    getVehicleDeploymentPlanById(id: number): Observable<VehicleDeploymentPlanOutputDto> {
        return this.http.get<VehicleDeploymentPlanOutputDto>(`${this.backendUrl}/${id}`)
    }
}
