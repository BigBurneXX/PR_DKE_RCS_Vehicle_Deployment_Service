package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class VehicleDeploymentPlan extends MetaData{
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToMany
    private Set<Address> addresses;

    @OneToMany(mappedBy = "vehicleDeploymentPlan")
    private Set<TripSheet> tripSheets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
