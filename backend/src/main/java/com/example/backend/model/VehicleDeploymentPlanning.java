package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@PlanningSolution
@Entity
public class VehicleDeploymentPlanning extends MetaData {
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    @ManyToMany
    @JoinTable(
            name = "planning_vehicle",
            joinColumns = @JoinColumn(name = "planning_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private Set<Vehicle> vehicles = new HashSet<>();

    @PlanningEntityCollectionProperty
    @ManyToMany
    @JoinTable(
            name = "planning_person",
            joinColumns = @JoinColumn(name = "planning_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> persons = new HashSet<>();

    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<VehicleDeploymentPlan> plans = new HashSet<>();

    @PlanningScore
    private HardSoftScore score;
}
