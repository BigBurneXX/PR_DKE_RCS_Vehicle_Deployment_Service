import { VehicleDeploymentPlanning } from '../vehicle-deployment-planning/VehicleDeploymentPlanning';

export interface VehicleDeploymentService {
  id: number | null;
  serviceName: string;
  description: string;
  vehicleDeploymentPlannings: VehicleDeploymentPlanning[];
}
