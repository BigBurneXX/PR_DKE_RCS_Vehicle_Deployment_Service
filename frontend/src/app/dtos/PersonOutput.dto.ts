import {LocationDto} from "./Location.dto";

export interface PersonOutputDto {
    id: number;
    name: string;
    creationDate: Date;
    lastModifiedDate: Date;
    version: number;
    personId: number;
    startLocation: LocationDto;
    endLocation: LocationDto;
    hasWheelchair: boolean;
    selected?: boolean;
}