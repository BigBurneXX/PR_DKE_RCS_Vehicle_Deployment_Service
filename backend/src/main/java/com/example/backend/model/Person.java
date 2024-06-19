package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PlanningEntity
@Entity
public class Person extends MetaData {
    @ManyToOne
    private Location startLocation;
    @ManyToOne
    private Location endLocation;
    private int demand = 1; // Set to 1, as each person occupies one seat
    private boolean hasWheelchair = false;

    @PlanningVariable(valueRangeProviderRefs = "vehicleRange")
    @ManyToOne
    private Vehicle vehicle;

    @PlanningVariable(valueRangeProviderRefs = "personRange")
    @ManyToOne
    private Person previousPerson;

    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
