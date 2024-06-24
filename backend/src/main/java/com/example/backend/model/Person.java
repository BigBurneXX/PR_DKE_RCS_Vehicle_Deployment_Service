package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Location startLocation;
    @ManyToOne
    private Location endLocation;

    @PlanningVariable(valueRangeProviderRefs = "vehicleRange")
    @ManyToOne
    private Vehicle assignedVehicle;
}
