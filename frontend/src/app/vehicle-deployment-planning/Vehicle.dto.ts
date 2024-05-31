

export interface VehicleDto {
  id: number
  vehicle_name: string;
  vehicle_type: string;
  seats: number;
  wheelchair: string;
  start_coordinates: string;
  end_coordinates: string;
  selected?: boolean;
}
