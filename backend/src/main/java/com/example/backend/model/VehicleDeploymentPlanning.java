package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleDeploymentPlanning extends MetaData {
    @ManyToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Person> persons;

    @ManyToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "vehicleDeploymentPlanning", cascade = CascadeType.ALL)
    private Set<VehicleDeploymentPlan> plans = new HashSet<>();
}
