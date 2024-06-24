import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {PersonInputDto} from "../component/dtos/PersonInput.dto";
import {VehicleInputDto} from "../component/dtos/VehicleInput.dto";
import {VehicleDeploymentPlanningInputDto} from "../component/dtos/VehicleDeploymentPlanningInput.dto";
import {VehicleDeploymentPlanDto} from "../new-trip-sheet/VehicleDeploymentPlan.dto";
import {AddressInputDto} from "../component/dtos/AddressInput.dto";

@Injectable({
    providedIn: 'root'
})
export class VehicleDeploymentPlanningService {
    private peopleUrl = 'http://localhost:8080/people';
    private vehicleUrl = 'http://localhost:8081/transport-services/vehicles';
    private backendUrl = 'http://localhost:8082/';

    constructor(private http: HttpClient) { }

    getPeople(filters?: any): Observable<PersonInputDto[]> {
        let params = new HttpParams();
        if (filters) {
            Object.keys(filters).forEach(key => {
                if (filters[key] !== null && filters[key] !== undefined) {
                    params = params.set(key, filters[key]);
                }
            });
        }
        return this.http.get<PersonInputDto[]>(this.peopleUrl, { params });
    }

    getVehicles(filters?: any): Observable<VehicleInputDto[]> {
        let params = new HttpParams();
        if (filters) {
            Object.keys(filters).forEach(key => {
                if (filters[key] !== null && filters[key] !== undefined) {
                    params = params.set(key, filters[key]);
                }
            });
        }
        return this.http.get<VehicleInputDto[]>(this.vehicleUrl, { params });
    }

    getAddresses(filters?: any): Observable<AddressInputDto[]> {
        for(PersonDto person: persons) {
            this.http.get()
        }
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
