package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TripSheet extends MetaData{
    @ManyToOne
    @JoinColumn(name="vehicleDeploymentPlan_id", nullable = false)
    private VehicleDeploymentPlan vehicleDeploymentPlan;

    /*
    @ManyToMany
    private Set<Address> addresses = new HashSet<>();
*/
    @ManyToMany
    private Set<Location> locations = new HashSet<>();
    /*
    public void addAddress (Location location) {
        locations.add(location);
    }*/
}
