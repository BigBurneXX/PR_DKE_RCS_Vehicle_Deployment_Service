import { VehicleDto } from './Vehicle.dto';
import { PersonDto } from './Person.dto';
import { VehicleDeploymentPlan } from '../vehicle-deployment-plan/VehicleDeploymentPlan';

export interface VehicleDeploymentPlanning {
  id: number;
  persons: PersonDto[];
  vehicles: VehicleDto[];
  plans: VehicleDeploymentPlan[];
}
