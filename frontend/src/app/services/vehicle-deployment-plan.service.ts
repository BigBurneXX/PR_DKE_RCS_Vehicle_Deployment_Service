import {HttpClient} from '@angular/common/http';
import {forkJoin, Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {VehicleDeploymentPlanOutputDto} from "../dtos/VehicleDeploymentPlanOutput.dto";
import {PersonInputDto} from "../dtos/PersonInput.dto";
import {PersonOutputDto} from "../dtos/PersonOutput.dto";

@Injectable({
    providedIn: 'root'
})
export class VehicleDeploymentPlanService {
    private baseDataUrl = 'http://localhost:8080';
    private backendUrl = 'http://localhost:8082/vehicle-plans';

    constructor(private http: HttpClient) { }

    getVehicleDeploymentPlans(): Observable<VehicleDeploymentPlanOutputDto[]> {
        return this.http.get<VehicleDeploymentPlanOutputDto[]>(this.backendUrl);
    }

    getVehicleDeploymentPlanById(id: number): Observable<VehicleDeploymentPlanOutputDto> {
        return this.http.get<VehicleDeploymentPlanOutputDto>(`${this.backendUrl}/${id}`)
    }

    getPeopleName(persons: PersonOutputDto[]): Observable<PersonInputDto[]> {
        const personRequests: Observable<PersonInputDto>[] = persons.map(person =>
            this.http.get<PersonInputDto>(`${this.baseDataUrl}/people/${person.personId}`)
        );
        return forkJoin(personRequests);
    }
}
