package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TripSheet extends MetaData {
    @ManyToOne
    private Vehicle vehicle;
    @ManyToMany
    private Set<Person> persons = new HashSet<>();
    @ManyToMany
    private List<Location> locations = new ArrayList<>();

    @ManyToOne
    private VehicleDeploymentPlan vehicleDeploymentPlan;
}
