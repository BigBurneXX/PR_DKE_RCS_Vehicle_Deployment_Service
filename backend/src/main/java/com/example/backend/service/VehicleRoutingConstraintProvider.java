package com.example.backend.service;

import com.example.backend.model.Person;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;

/**
 * This class defines the constraints for vehicle routing problems.
 * It implements the ConstraintProvider interface to provide a set of constraints
 * that must be satisfied for an optimal solution.
 */
public class VehicleRoutingConstraintProvider implements ConstraintProvider {

    /**
     * Defines the list of constraints for the vehicle routing problem.
     *
     * @param constraintFactory the factory to create constraints
     * @return an array of constraints
     */
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                vehicleCapacityConstraint(constraintFactory),
                wheelchairCompatibilityConstraint(constraintFactory),
                minimizeDistance(constraintFactory)
        };
    }

    /**
     * Defines a constraint that ensures the vehicle capacity is not exceeded.
     *
     * @param constraintFactory the factory to create constraints
     * @return a constraint for vehicle capacity
     */
    private Constraint vehicleCapacityConstraint(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Person.class)
                .groupBy(Person::getAssignedVehicle, ConstraintCollectors.count())
                .filter((vehicle, count) -> count > vehicle.getSeats())
                .penalize(HardSoftScore.ONE_HARD, (vehicle, count) -> count - vehicle.getSeats())
                .asConstraint("Vehicle capacity");
    }

    /**
     * Defines a constraint that ensures wheelchair compatibility for assigned vehicles.
     *
     * @param constraintFactory the factory to create constraints
     * @return a constraint for wheelchair compatibility
     */
    private Constraint wheelchairCompatibilityConstraint(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Person.class)
                .filter(Person::isHasWheelchair)
                .filter(person -> person.getAssignedVehicle() == null || !person.getAssignedVehicle().isCanCarryWheelchair())
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Wheelchair compatibility");
    }

    /**
     * Defines a constraint that minimizes the total travel distance.
     *
     * @param constraintFactory the factory to create constraints
     * @return a constraint to minimize distance
     */
    private Constraint minimizeDistance(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Person.class)
                //TODO: Should access openrouteservice to calculate distance!
                .penalize(HardSoftScore.ONE_SOFT,  person -> (int) (person.getStartLocation().getDistanceTo(person.getAssignedVehicle().getStartLocation())
                        + person.getEndLocation().getDistanceTo(person.getAssignedVehicle().getEndLocation())))
                .asConstraint("minimize distance");
    }
}