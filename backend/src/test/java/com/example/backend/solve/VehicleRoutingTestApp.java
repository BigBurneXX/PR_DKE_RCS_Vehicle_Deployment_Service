package com.example.backend.solve;

import com.example.backend.model.Location;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class VehicleRoutingTestApp {
    public static void main(String[] args) {
        SolverFactory<VehicleRoutingSolution> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(VehicleRoutingSolution.class)
                .withEntityClasses(PersonSolve.class)
                .withConstraintProviderClass(VehicleRoutingSolveConstraintProvider.class)
                // The solver runs only for 5 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(10)));

        Solver<VehicleRoutingSolution> solver = solverFactory.buildSolver();
        VehicleRoutingSolution solvedSolution = solver.solve(getVehicleRoutingSolution());

        Map<VehicleSolve, List<PersonSolve>> vehiclePersonMap = solvedSolution.getPersonList().stream()
                .collect(Collectors.groupingBy(PersonSolve::getAssignedVehicle));

        solvedSolution.setVehiclePlans(getVehiclePlans(vehiclePersonMap));

        for (VehiclePlan vehiclePlan : solvedSolution.getVehiclePlans()) {
            System.out.println("Vehicle: " + vehiclePlan.getVehicle().getId());
            System.out.println("Persons: " + vehiclePlan.getPersons().stream().map(PersonSolve::getId).collect(Collectors.joining(", ")));
            System.out.println("Total Distance: " + vehiclePlan.getTotalDistance());
            System.out.println("Optimized Route: " + vehiclePlan.getOptimizedRoute().stream()
                    .map(location -> "(" + location.getLatitude() + ", " + location.getLongitude() + ")")
                    .collect(Collectors.joining(" -> ")));
            System.out.println();
        }
    }

    private static List<VehiclePlan> getVehiclePlans(Map<VehicleSolve, List<PersonSolve>> vehiclePersonMap) {
        List<VehiclePlan> vehiclePlans = new ArrayList<>();
        for (Map.Entry<VehicleSolve, List<PersonSolve>> entry : vehiclePersonMap.entrySet()) {
            VehicleSolve vehicle = entry.getKey();
            List<PersonSolve> assignedPersons = entry.getValue();
            VehiclePlan vehiclePlan = new VehiclePlan(vehicle, assignedPersons);
            vehiclePlan.calculateTotalDistance();
            vehiclePlan.generateOptimizedRoute();
            vehiclePlans.add(vehiclePlan);
        }
        return vehiclePlans;
    }

    private static VehicleRoutingSolution getVehicleRoutingSolution() {
        List<VehicleSolve> vehicles = Arrays.asList(
                new VehicleSolve("Vehicle1", 4, new Location(0, 0), new Location(10, 10), true),
                new VehicleSolve("Vehicle2", 3, new Location(2, 2), new Location(12, 12), false)
        );

        List<PersonSolve> persons = Arrays.asList(
                new PersonSolve("Person1", new Location(1, 1), new Location(11, 11), false),
                new PersonSolve("Person2", new Location(3, 3), new Location(13, 13), false),
                new PersonSolve("Person3", new Location(4, 4), new Location(14, 14), true),
                new PersonSolve("Person4", new Location(5, 5), new Location(15, 15), false)
        );

        return new VehicleRoutingSolution(vehicles, persons);
    }
}
