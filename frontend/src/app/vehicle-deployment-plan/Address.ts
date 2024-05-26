import { VehicleDeploymentPlan } from './VehicleDeploymentPlan';

export interface Address {
  id: number | null;
  street: string;
  houseNo: string;
  townId: number | null;
  coordinates: string;
  vehicleDeploymentPlan: VehicleDeploymentPlan;
}
