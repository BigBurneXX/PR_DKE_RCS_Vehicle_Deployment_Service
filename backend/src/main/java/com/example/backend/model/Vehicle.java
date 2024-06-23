package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vehicle extends MetaData {
    private Long vehicleId;
    private int seats;
    private boolean canCarryWheelchair;

    @ManyToOne
    @JoinColumn(name = "start_location_id")
    private Location startLocation;
    @ManyToOne
    @JoinColumn(name = "end_location_id")
    private Location endLocation;

    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlan_id")
    private VehicleDeploymentPlan vehicleDeploymentPlan;
    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}