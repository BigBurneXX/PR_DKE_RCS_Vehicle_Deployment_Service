import { Vehicle } from './Vehicle';
import { Person } from './Person';
import { VehicleDeploymentPlan } from '../vehicle-deployment-plan/VehicleDeploymentPlan';
import { VehicleDeploymentService } from '../vehicle-deployment-service/VehicleDeploymentService';

export interface VehicleDeploymentPlanning {
  id: number | null;
  persons: Person[];
  vehicles: Vehicle[];
  plans: VehicleDeploymentPlan[];
  vehicleDeploymentService: VehicleDeploymentService;
}
