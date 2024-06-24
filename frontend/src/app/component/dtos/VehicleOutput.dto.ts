import {LocationDto} from "./Location.dto";

export interface VehicleOutputDto {
    id: number;
    creationDate: Date;
    lastModifiedDate: Date;
    version: number;
    vehicleId: number;
    seats: number;
    canCarryWheelchair: boolean;
    startLocation: LocationDto;
    endLocation: LocationDto;
}