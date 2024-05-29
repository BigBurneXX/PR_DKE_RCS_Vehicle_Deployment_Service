import { Vehicle } from './Vehicle';
import { Person } from './Person';
import { VehicleDeploymentPlan } from '../vehicle-deployment-plan/VehicleDeploymentPlan';

export interface VehicleDeploymentPlanning {
  id: number | null;
  persons: Person[];
  vehicles: Vehicle[];
  plans: VehicleDeploymentPlan[];
}
