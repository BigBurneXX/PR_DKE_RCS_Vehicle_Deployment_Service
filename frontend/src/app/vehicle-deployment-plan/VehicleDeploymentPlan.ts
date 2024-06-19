import { Address } from './Address';
import { VehicleDeploymentPlanning } from '../vehicle-deployment-planning/VehicleDeploymentPlanning';

export class VehicleDeploymentPlan {
  id: number;
  vehicleId: number;
  addresses: Address[];
  vehicleDeploymentPlanning: VehicleDeploymentPlanning;

  constructor(id: number, vehicleId: number, addresses: Address[], vehicleDeploymentPlanning: VehicleDeploymentPlanning) {
    this.id = id;
    this.vehicleId = vehicleId;
    this.addresses = addresses;
    this.vehicleDeploymentPlanning = vehicleDeploymentPlanning
  }
}
