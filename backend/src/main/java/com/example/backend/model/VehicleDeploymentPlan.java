package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class VehicleDeploymentPlan extends Route {
    @OneToMany
    private Set<TripSheet> tripSheets = new HashSet<>();

    @ManyToOne
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;

    public void addTripSheet(TripSheet tripSheet) {
        tripSheets.add(tripSheet);
    }
}
