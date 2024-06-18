package com.example.backend.model;

import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
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
    private Set<Person> persons;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Vehicle> vehicles;

    @PlanningEntityCollectionProperty
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Location> locations;

    @PlanningScore
    private HardSoftScore score;

    public VehicleDeploymentPlanning(VehicleDeploymentPlanningInputDTO vehicleDeploymentPlanningInputDTO) {
        this.persons = vehicleDeploymentPlanningInputDTO.persons();
        this.vehicles = vehicleDeploymentPlanningInputDTO.vehicles();
        this.locations = new HashSet<>();
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.persons().stream().map(Person::getStartLocation).toList());
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.persons().stream().map(Person::getEndLocation).toList());
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.vehicles().stream().map(Vehicle::getStartLocation).toList());
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.vehicles().stream().map(Vehicle::getEndLocation).toList());
    }
}
