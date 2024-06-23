package com.example.backend.solve;

import com.example.backend.model.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class VehiclePlan {
    private VehicleSolve vehicle;
    private List<PersonSolve> persons;
    private List<Location> optimizedRoute = new ArrayList<>();
    private double totalDistance = 0.0;

    public VehiclePlan(VehicleSolve vehicle, List<PersonSolve> persons) {
        this.vehicle = vehicle;
        this.persons = persons;
    }

    public void generateOptimizedRoute() {
        this.totalDistance = 0.0;
        List<Location> route = new ArrayList<>();
        route.add(vehicle.getStartLocation());

        List<PersonSolve> remainingPersons = new ArrayList<>(persons);
        List<PersonSolve> personsInVehicle = new ArrayList<>();
        Location currentLocation = vehicle.getStartLocation();
        PersonSolve nearestPerson = null;

        while (!remainingPersons.isEmpty() || !personsInVehicle.isEmpty()) {
            Location nearestLocation = null;
            nearestPerson = null;
            boolean isStartLocation = true;
            double shortestDistance = Double.MAX_VALUE;

            // Check nearest start location of remaining persons
            for (PersonSolve person : remainingPersons) {
                double distance = currentLocation.getDistanceTo(person.getStartLocation());
                // as distanceMatrixService is not functioning right now
                //double distance = distanceMatrixService.getDistanceMatrix(currentLocation, person.getStartLocation()).getDistances()[0][1];
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearestLocation = person.getStartLocation();
                    nearestPerson = person;
                }
            }

            // Check nearest end location of persons in the vehicle
            for (PersonSolve person : personsInVehicle) {
                double distance = currentLocation.getDistanceTo(person.getEndLocation());
                // as distanceMatrixService is not functioning right now
                //double distance = distanceMatrixService.getDistanceMatrix(currentLocation, person.getEndLocation()).getDistances()[0][1];
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearestLocation = person.getEndLocation();
                    nearestPerson = person;
                    isStartLocation = false;
                }
            }

            route.add(nearestLocation);
            currentLocation = nearestLocation;

            if (isStartLocation) {
                remainingPersons.remove(nearestPerson);
                personsInVehicle.add(nearestPerson);
            } else {
                personsInVehicle.remove(nearestPerson);
            }

            System.out.print("currentDistance: " + shortestDistance + ", ");
            totalDistance += shortestDistance;
            System.out.println("totalDistance: " + totalDistance);
        }
        if(nearestPerson != null)
            totalDistance += nearestPerson.getEndLocation().getDistanceTo(vehicle.getEndLocation());
        route.add(vehicle.getEndLocation());
        this.optimizedRoute = route;
    }

    public void calculateTotalDistance() {
        double distance = 0.0;
        Location currentLocation = vehicle.getStartLocation();

        for (PersonSolve person : persons) {
            distance += currentLocation.getDistanceTo(person.getStartLocation());
            currentLocation = person.getStartLocation();
        }

        distance += currentLocation.getDistanceTo(vehicle.getEndLocation());
        this.totalDistance = distance;
    }
}
