package com.example.backend.model;

import jakarta.persistence.*;
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
public class VehicleDeploymentPlan extends MetaData{
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToMany
    @JoinTable(
            name = "plan_person",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "plan_route", joinColumns = @JoinColumn(name = "plan_id"))
    private List<Location> route = new ArrayList<>();

    @OneToMany(mappedBy = "vehicleDeploymentPlan")
    private Set<TripSheet> tripSheets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
