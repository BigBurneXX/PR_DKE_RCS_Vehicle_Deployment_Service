package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@PlanningEntity
public class VehicleDeploymentPlan extends MetaData{
    @PlanningVariable(valueRangeProviderRefs = "vehicleRange")
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @PlanningEntityCollectionProperty
    @ManyToMany
    @JoinTable(
            name = "vehicle_deployment_plan_persons",
            joinColumns = @JoinColumn(name = "vehicle_deployment_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons = new HashSet<>();

    @ManyToMany
    private Set<Location> coordinates = new HashSet<>();

    @OneToMany(mappedBy = "vehicleDeploymentPlan")
    private Set<TripSheet> tripSheets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
