import { VehicleDeploymentPlanning } from './VehicleDeploymentPlanning';

export interface Person {
  id: number | null;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  startAddressId: number | null;
  targetAddressId: number | null;
  hasWheelChair: boolean;
  vehicleDeploymentPlanning: VehicleDeploymentPlanning;
}
