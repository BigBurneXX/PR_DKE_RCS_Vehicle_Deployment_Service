import {LocationDto} from "./Location.dto";
import {VehicleDeploymentPlanDto} from "./VehicleDeploymentPlan.dto";

export class TripSheetInputDto {
    vehicleDeploymentPlan: VehicleDeploymentPlanDto
    locations: LocationDto[]

    constructor(vehicleDeploymentPlan: VehicleDeploymentPlanDto, locations: LocationDto[]) {
        this.vehicleDeploymentPlan = vehicleDeploymentPlan;
        this.locations = locations;
    }
}