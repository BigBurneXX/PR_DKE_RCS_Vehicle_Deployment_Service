export class TripSheet {
  id: number | null;
  vehicleDeploymentPlanId: number;
  visitStatus: boolean[];

  constructor(id: number, vehicleDeploymentPlanId: number, visitStatus: boolean[]) {
    this.id = id;
    this.vehicleDeploymentPlanId = vehicleDeploymentPlanId;
    this.visitStatus = visitStatus;
  }
}
