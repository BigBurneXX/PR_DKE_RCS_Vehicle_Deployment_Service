import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin, map, Observable } from 'rxjs';
import { PersonInputDto } from "../dtos/PersonInput.dto";
import { VehicleInputDto } from "../dtos/VehicleInput.dto";
import { VehicleDeploymentPlanningInputDto } from "../dtos/VehicleDeploymentPlanningInput.dto";
import { AddressInputDto } from "../dtos/AddressInput.dto";
import { VehicleDeploymentPlanningOutputDto } from "../dtos/VehicleDeploymentPlanningOutput.dto";

@Injectable({
    providedIn: 'root'
})
export class VehicleDeploymentPlanningService {
    private baseDataUrl = 'http://localhost:8080';
    private transportServicePortalUrl = 'http://localhost:8081';
    private backendUrl = 'http://localhost:8082/vehicle-plannings';

    constructor(private http: HttpClient) { }

    getPeople(): Observable<PersonInputDto[]> {
        return this.http.get<PersonInputDto[]>(`${this.baseDataUrl}/people`);
    }

    getVehicles(): Observable<VehicleInputDto[]> {
        return this.http.get<VehicleInputDto[]>(`${this.transportServicePortalUrl}/transport-services/vehicles`);
    }

    getAddresses(persons: PersonInputDto[]): Observable<AddressInputDto[]> {
        const addressRequests: Observable<AddressInputDto[]>[] = persons.map(person =>
            forkJoin([
                this.http.get<AddressInputDto>(`${this.baseDataUrl}/addresses/${person.start_address}`),
                this.http.get<AddressInputDto>(`${this.baseDataUrl}/addresses/${person.target_address}`)
            ])
        );

        return forkJoin(addressRequests).pipe(
            map((results: AddressInputDto[][]) => results.flat())  // Flatten the array of arrays
        );
    }

    postVehicleDeploymentPlanning(data: VehicleDeploymentPlanningInputDto): Observable<any> {
        return this.http.post<VehicleDeploymentPlanningInputDto>(this.backendUrl, data);
    }

    getVehicleDeploymentPlannings(): Observable<VehicleDeploymentPlanningOutputDto[]> {
        return this.http.get<VehicleDeploymentPlanningOutputDto[]>(this.backendUrl);
    }

    getVehicleDeploymentPlanning(id: number | undefined): Observable<VehicleDeploymentPlanningOutputDto> {
        return this.http.get<VehicleDeploymentPlanningOutputDto>(`${this.backendUrl}/${id}`);
    }
}
