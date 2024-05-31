import { VehicleDto } from './Vehicle.dto';
import { PersonDto } from './Person.dto';

export interface VehicleDeploymentPlanningInputDto {
    persons: PersonDto[];
    vehicles: VehicleDto[];
}

export class VehicleDeploymentPlanningInput implements VehicleDeploymentPlanningInputDto {
    persons: PersonDto[] = [];
    vehicles: VehicleDto[] = [];
}