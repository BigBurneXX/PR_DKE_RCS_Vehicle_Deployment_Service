import {PersonOutputDto} from "./PersonOutput.dto";
import {LocationDto} from "./Location.dto";
import {VehicleOutputDto} from "./VehicleOutput.dto";

export interface TripSheetOutputDto {
    id: number;
    creationDate: Date;
    lastModifiedDate: Date;
    version: number;
    vehicle: VehicleOutputDto;
    persons: PersonOutputDto[];
    locations: LocationDto[];
    vehicleDeploymentPlanId: number;
}