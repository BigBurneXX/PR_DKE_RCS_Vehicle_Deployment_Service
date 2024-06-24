import {LocationDto} from "./Location.dto";

export interface PersonOutputDto {
    id: number;
    creationDate: Date;
    lastModifiedDate: Date;
    version: number;
    personId: number;
    startLocation: LocationDto;
    endLocation: LocationDto;
    hasWheelchair: boolean;
}