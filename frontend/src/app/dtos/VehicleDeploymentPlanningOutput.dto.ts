import {PersonOutputDto} from "./PersonOutput.dto";
import {VehicleOutputDto} from "./VehicleOutput.dto";
import {VehicleDeploymentPlanOutputDto} from "./VehicleDeploymentPlanOutput.dto";

export interface VehicleDeploymentPlanningOutputDto {
    id: number;
    name: string;
    creationDate: Date;
    lastModifiedDate: Date;
    version: number;
    persons: PersonOutputDto[];
    vehicles: VehicleOutputDto[];
    plans: VehicleDeploymentPlanOutputDto[];
}