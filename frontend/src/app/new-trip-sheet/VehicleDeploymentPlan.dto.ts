import {VehicleDto} from "../vehicle-deployment-planning/Vehicle.dto";
import {PersonDto} from "../vehicle-deployment-planning/Person.dto";
import {LocationDto} from "./Location.dto";

export class VehicleDeploymentPlanDto {
    id: number
    vehicle: VehicleDto
    persons: PersonDto[]
    locations: LocationDto[]

    constructor(id: number, vehicle: VehicleDto, persons: PersonDto[], locations: LocationDto[]) {
        this.id = id;
        this.vehicle = vehicle;
        this.persons = persons;
        this.locations = locations;
    }
}