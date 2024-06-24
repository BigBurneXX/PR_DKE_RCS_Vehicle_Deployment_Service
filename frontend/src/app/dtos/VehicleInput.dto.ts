export interface VehicleInputDto {
    id: number;
    vehicle_type: string;
    vehicle_name: string;
    seats: number;
    wheelchair: string;
    start_coordinates: string;
    end_coordinates: string;
    selected?: boolean;
}