import { Address } from './Address';
import { VehicleDeploymentPlanning } from '../vehicle-deployment-planning/VehicleDeploymentPlanning';

export interface VehicleDeploymentPlan {
  id: number | null;
  vehicleId: number | null;
  addresses: Address[];
  vehicleDeploymentPlanning: VehicleDeploymentPlanning;
}
