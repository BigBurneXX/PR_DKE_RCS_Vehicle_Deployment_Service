import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PersonDto } from '../vehicle-deployment-planning/Person.dto';
import { VehicleDto } from "../vehicle-deployment-planning/Vehicle.dto";
import {VehicleDeploymentPlanningInputDto} from "../vehicle-deployment-planning/VehicleDeploymentPlanningInput.dto";
import {VehicleDeploymentPlanDto} from "../new-trip-sheet/VehicleDeploymentPlan.dto";

@Injectable({
    providedIn: 'root'
})
export class VehicleDeploymentPlanningService {
    private peopleUrl = 'http://localhost:8080/peopleRead';
    private vehicleUrl = 'http://localhost:8081/vehicles';
    private backendUrl = 'http://localhost:8082/';

    constructor(private http: HttpClient) { }

    getPeople(filters?: any): Observable<PersonDto[]> {
        let params = new HttpParams();
        if (filters) {
            Object.keys(filters).forEach(key => {
                if (filters[key] !== null && filters[key] !== undefined) {
                    params = params.set(key, filters[key]);
                }
            });
        }
        return this.http.get<PersonDto[]>(this.peopleUrl, { params });
    }

    getVehicles(filters?: any): Observable<VehicleDto[]> {
        let params = new HttpParams();
        if (filters) {
            Object.keys(filters).forEach(key => {
                if (filters[key] !== null && filters[key] !== undefined) {
                    params = params.set(key, filters[key]);
                }
            });
        }
        return this.http.get<VehicleDto[]>(this.vehicleUrl, { params });
    }

    postVehicleDeploymentPlanning(data: VehicleDeploymentPlanningInputDto): Observable<any> {
        return this.http.post<VehicleDeploymentPlanningInputDto>(this.backendUrl, data);
    }

    getVehicleDeploymentPlans(filters?: any): Observable<VehicleDeploymentPlanDto[]> {
        let params = new HttpParams();
        if (filters) {
            Object.keys(filters).forEach(key => {
                if (filters[key] !== null && filters[key] !== undefined) {
                    params = params.set(key, filters[key]);
                }
            });
        }
        return this.http.get<VehicleDeploymentPlanDto[]>(`${this.backendUrl}vehicle-deployment-plans`, { params });
    }
}
