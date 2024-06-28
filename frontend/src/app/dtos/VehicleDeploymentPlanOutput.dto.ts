import {VehicleOutputDto} from "./VehicleOutput.dto";
import {PersonOutputDto} from "./PersonOutput.dto";
import {LocationDto} from "./Location.dto";

export interface VehicleDeploymentPlanOutputDto {
    id: number;
    name: string;
    creationDate: Date;
    lastModifiedDate: Date;
    version: number;
    vehicle: VehicleOutputDto;
    persons: PersonOutputDto[];
    locations: LocationDto[];
    tripSheetIds: number[];
    planningId: number;
}