package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@Getter
@Setter
@NoArgsConstructor
@PlanningEntity
@Entity
public class Person extends MetaData {
    private Long personId;
    private boolean hasWheelchair = false;

    @ManyToOne
    @JoinColumn(name = "start_location_id")
    private Location startLocation;
    @ManyToOne
    @JoinColumn(name = "end_location_id")
    private Location endLocation;

    @PlanningVariable(valueRangeProviderRefs = "vehicleRange")
    @ManyToOne
    private Vehicle assignedVehicle;

    @ManyToOne
    @JoinColumn(name = "tripsheet_id")
    private TripSheet tripSheet;
    @ManyToOne
    @JoinColumn(name = "vehicle_deployment_plan_id")
    private VehicleDeploymentPlan vehicleDeploymentPlan;
    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
