package com.example.backend.model;

import com.example.backend.response.DistanceMatrixResponse;
import com.example.backend.service.DistanceMatrixService;
import lombok.RequiredArgsConstructor;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import static org.optaplanner.core.api.score.stream.ConstraintCollectors.*;

@RequiredArgsConstructor
public class VehicleRoutingConstraintProvider implements ConstraintProvider {
    DistanceMatrixService distanceMatrixService;

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                vehicleCapacity(constraintFactory),
                distanceFromPreviousStandstill(constraintFactory),
                distanceFromVehicleStartLocation(constraintFactory)
        };
    }

    private Constraint vehicleCapacity(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Person.class)
                .groupBy(Person::getVehicle, sum(Person::getDemand))
                .filter((vehicle, demand) -> demand > vehicle.getCapacity())
                .penalize(HardSoftScore.ONE_HARD, (vehicle, demand) -> demand - vehicle.getCapacity())
                .asConstraint("vehicle capacity");
    }

    private Constraint distanceFromPreviousStandstill(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Person.class)
                .penalize(HardSoftScore.ONE_SOFT,
                        (person) -> {
                            if (person.getPreviousPerson() == null) {
                                return 0;
                            }
                            return calculateDistance(person.getPreviousPerson().getEndLocation(), person.getStartLocation());
                        })
                .asConstraint("distance from previous standstill");
    }

    private Constraint distanceFromVehicleStartLocation(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Person.class)
                .filter(person -> person.getPreviousPerson() == null)
                .penalize(HardSoftScore.ONE_SOFT,
                        (person) -> calculateDistance(person.getVehicle().getStartLocation(), person.getStartLocation()))
                .asConstraint("distance from vehicle start location");
    }

    private int calculateDistance(Location location1, Location location2) {
        DistanceMatrixResponse response = distanceMatrixService.getDistanceMatrix(location1, location2);
        if(response != null && response.getDurations() != null && response.getDurations().length > 0) {
            // making the difference bigger by extrapolating
            return (int) Math.pow(response.getDistances()[0][0], 2);
        }
        //should raise an error
        return Integer.MAX_VALUE;
    }
}