import {VehicleDeploymentPlanning} from "./VehicleDeploymentPlanning";

export interface Vehicle {
  id: number
  vehicle_name: string;
  vehicle_type: string;
  seats: number;
  wheelchair: string;
  start_coordinates: string;
  end_coordinates: string;
  vehicle_deployment_planning: VehicleDeploymentPlanning;
}
