package com.example.backend.solve;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@PlanningSolution
public class VehicleRoutingSolution {

    @ValueRangeProvider(id = "vehicleRange")
    @ProblemFactCollectionProperty
    private List<VehicleSolve> vehicleList;

    @PlanningEntityCollectionProperty
    private List<PersonSolve> personList;

    private List<VehiclePlan> vehiclePlans;

    @PlanningScore
    private HardSoftScore score;

    public VehicleRoutingSolution(List<VehicleSolve> vehicleList, List<PersonSolve> personList) {
        this.vehicleList = vehicleList;
        this.personList = personList;
    }
}

