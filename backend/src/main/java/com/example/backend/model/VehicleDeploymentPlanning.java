package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Set<Vehicle> vehicles = new HashSet<>();

    @PlanningEntityCollectionProperty
    @ManyToMany
    private Set<Person> persons = new HashSet<>();

    @OneToMany
    private Set<VehicleDeploymentPlan> plans = new HashSet<>();

    @PlanningScore
    private HardSoftScore score;
}
