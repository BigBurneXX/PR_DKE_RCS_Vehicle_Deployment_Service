package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a vehicle deployment plan, which is a specific type of route and contains all trip sheets for the plan.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class VehicleDeploymentPlan extends Route {
    @OneToMany
    private Set<TripSheet> tripSheets = new HashSet<>();

    @ManyToOne
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;

    /**
     * Adds a trip sheet to the vehicle deployment plan.
     *
     * @param tripSheet The trip sheet to add.
     */
    public void addTripSheet(TripSheet tripSheet) {
        tripSheets.add(tripSheet);
    }
}
