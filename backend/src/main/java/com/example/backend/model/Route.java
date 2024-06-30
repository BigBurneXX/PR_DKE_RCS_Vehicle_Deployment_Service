package com.example.backend.model;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract class representing a route, including a vehicle, persons, and locations.
 */
@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class Route extends MetaData {
    @ManyToOne
    private Vehicle vehicle;
    @ManyToMany
    private Set<Person> persons = new HashSet<>();
    @ManyToMany
    private List<Location> locations = new ArrayList<>();

    /**
     * Optimizes the route by finding the shortest path through all locations with a set start location (the vehicle's
     * start location) and a set end location (the vehicle's end location)
     */
    public void getOptimizedRoute() {
        List<Location> route = new ArrayList<>();
        route.add(vehicle.getStartLocation());

        List<Person> remainingPersons = new ArrayList<>(persons);
        List<Person> personsInVehicle = new ArrayList<>();
        Location currentLocation = vehicle.getStartLocation();


        while (!remainingPersons.isEmpty() || !personsInVehicle.isEmpty()) {
            Location nearestLocation = null;
            Person nearestPerson = null;
            boolean isStartLocation = true;
            double shortestDistance = Double.MAX_VALUE;

            // Check nearest start location of remaining persons
            for (Person person : remainingPersons) {
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
            for (Person person : personsInVehicle) {
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
        }
        route.add(vehicle.getEndLocation());
        this.locations = route;
    }
}
