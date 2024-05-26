export class TripSheet {
  vehicleDeploymentPlanId: number;
  visitStatus: boolean[];

  constructor(vehicleDeploymentPlanId: number, visitStatus: boolean[]) {
    this.vehicleDeploymentPlanId = vehicleDeploymentPlanId;
    this.visitStatus = visitStatus;
  }
}
