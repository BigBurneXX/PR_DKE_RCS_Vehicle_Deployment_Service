import {PersonInputDto} from "./PersonInput.dto";
import {VehicleInputDto} from "./VehicleInput.dto";
import {AddressInputDto} from "./AddressInput.dto";

export class VehicleDeploymentPlanningInputDto {
    name: string | undefined;
    persons: PersonInputDto[] = [];
    vehicles: VehicleInputDto[] = [];
    addresses: AddressInputDto[] = [];
}