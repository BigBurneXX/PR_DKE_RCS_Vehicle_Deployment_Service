package com.example.backend.solve;

import com.example.backend.response.DistanceMatrixResponse;
import com.example.backend.service.DistanceMatrixService;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleRoutingSolveConstraintProvider implements ConstraintProvider {
    private final DistanceMatrixService distanceMatrixService = new DistanceMatrixService(new RestTemplate());

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                vehicleCapacityConstraint(constraintFactory),
                wheelchairCompatibilityConstraint(constraintFactory),
                minimizeDistance(constraintFactory)
        };
    }

    private Constraint vehicleCapacityConstraint(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(PersonSolve.class)
                .groupBy(PersonSolve::getAssignedVehicle, ConstraintCollectors.count())
                .filter((vehicle, count) -> count > vehicle.getCapacity())
                .penalize(HardSoftScore.ONE_HARD, (vehicle, count) -> count - vehicle.getCapacity())
                .asConstraint("Vehicle capacity");
    }

    private Constraint wheelchairCompatibilityConstraint(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(PersonSolve.class)
                .filter(PersonSolve::isHasWheelchair)
                .filter(person -> person.getAssignedVehicle() == null || !person.getAssignedVehicle().isCanCarryWheelchair())
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Wheelchair compatibility");
    }

    private Constraint minimizeDistance(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(PersonSolve.class)
                .penalize(HardSoftScore.ONE_SOFT,  person -> (int) (person.getStartLocation().getDistanceTo(person.getAssignedVehicle().getStartLocation())
                        + person.getEndLocation().getDistanceTo(person.getAssignedVehicle().getEndLocation())))
                .asConstraint("minimize distance");
    }
}

