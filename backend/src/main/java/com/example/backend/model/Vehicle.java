package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Vehicle")
public class Vehicle extends MetaData {
    private int seats;
    private boolean hasWheelchair;

    @ManyToOne
    private Location startLocation;
    @ManyToOne
    private Location endLocation;

    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}