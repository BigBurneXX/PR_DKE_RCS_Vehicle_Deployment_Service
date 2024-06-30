package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.Date;

/**
 * Represents a person with metadata, including start and end locations, and an assigned vehicle.
 */
@Getter
@Setter
@NoArgsConstructor
@PlanningEntity
@Entity
public class Person extends MetaData {
    private Long personId;
    private Date dateOfBirth;
    private boolean hasWheelchair = false;
    @ManyToOne
    private Location startLocation;
    @ManyToOne
    private Location endLocation;

    @PlanningVariable(valueRangeProviderRefs = "vehicleRange")
    @ManyToOne
    private Vehicle assignedVehicle;
}
