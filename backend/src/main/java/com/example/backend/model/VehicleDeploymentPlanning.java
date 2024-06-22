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
    @ValueRangeProvider(id = "personRange")
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Person> persons = new HashSet<>();

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Vehicle> vehicles = new HashSet<>();

    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Location> locations = new HashSet<>();

    @PlanningEntityCollectionProperty
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<VehicleDeploymentPlan> plans = new HashSet<>();

    @PlanningScore
    private HardSoftScore score;
}
