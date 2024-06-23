package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TripSheet extends MetaData{
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToMany
    @JoinTable(
            name = "tripsheet_person",
            joinColumns = @JoinColumn(name = "tripsheet_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "tripsheet_route", joinColumns = @JoinColumn(name = "tripsheet_id"))
    private List<Location> route = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="vehicleDeploymentPlan_id", nullable = false)
    private VehicleDeploymentPlan vehicleDeploymentPlan;
}
