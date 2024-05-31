package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleDeploymentPlanning extends MetaData {
    @ManyToMany(mappedBy = "vehicleDeploymentPlannings")
    private Set<Person> persons;

    @ManyToMany(mappedBy = "vehicleDeploymentPlannings")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "vehicleDeploymentPlanning", cascade = CascadeType.ALL)
    private Set<VehicleDeploymentPlan> plans = new HashSet<>();
}
