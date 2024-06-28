import {LocationDto} from "./Location.dto";

export interface VehicleOutputDto {
    id: number;
    name: string;
    creationDate: Date;
    lastModifiedDate: Date;
    version: number;
    vehicleId: number;
    type: string;
    seats: number;
    canCarryWheelchair: boolean;
    startLocation: LocationDto;
    endLocation: LocationDto;
}