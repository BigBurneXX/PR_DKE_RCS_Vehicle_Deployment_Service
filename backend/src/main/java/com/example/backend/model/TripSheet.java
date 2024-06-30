package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a trip sheet, which is a specific type of route.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TripSheet extends Route {
    @ManyToOne
    private VehicleDeploymentPlan vehicleDeploymentPlan;
}
