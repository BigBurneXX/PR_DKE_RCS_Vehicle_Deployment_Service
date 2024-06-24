package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class VehicleDeploymentPlan extends MetaData{
    @ManyToOne
    private Vehicle vehicle;
    @ManyToMany
    private Set<Person> persons = new HashSet<>();
    // locations represents the route
    @ManyToMany
    private List<Location> locations = new ArrayList<>();
    @OneToMany
    private Set<TripSheet> tripSheets = new HashSet<>();

    @ManyToOne
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;

    public void generateOptimizedRoute() {
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
