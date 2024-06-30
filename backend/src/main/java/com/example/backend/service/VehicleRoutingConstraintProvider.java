package com.example.backend.service;

import com.example.backend.model.Location;
import com.example.backend.model.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

/**
 * This class defines the constraints for vehicle routing problems.
 * It implements the ConstraintProvider interface to provide a set of constraints
 * that must be satisfied for an optimal solution.
 */
public class VehicleRoutingConstraintProvider implements ConstraintProvider {
    private static final String API_KEY = "5b3ce3597851110001cf62484632c7a923954e39bbdf8f1224470ce7";
    private static final String API_URL = "https://api.openrouteservice.org/v2/directions/driving-car";

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
     * <p>
     * This constraint uses the OpenRouteService API to calculate the distance between locations.
     * If the API call fails, it falls back to using a default distance calculation method.
     * </p>
     *
     * @param constraintFactory the factory to create constraints
     * @return a constraint to minimize distance
     */
    private Constraint minimizeDistance(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Person.class)
                .penalize(HardSoftScore.ONE_SOFT, person -> {
                    double startDistance = calculateDistance(person.getStartLocation(), person.getAssignedVehicle().getStartLocation());
                    double endDistance = calculateDistance(person.getEndLocation(), person.getAssignedVehicle().getEndLocation());

                    if (startDistance == -1.0) {
                        startDistance = person.getStartLocation().getDistanceTo(person.getAssignedVehicle().getStartLocation());
                    }
                    if (endDistance == -1.0) {
                        endDistance = person.getEndLocation().getDistanceTo(person.getAssignedVehicle().getEndLocation());
                    }

                    return (int) (startDistance + endDistance);
                })
                .asConstraint("minimize distance");
    }

    /**
     * Calculates the road distance between two geographical locations using the OpenRouteService API.
     * <p>
     * The method sends a request to the OpenRouteService API with the coordinates of the two locations
     * and parses the response to extract the distance in meters.
     * </p>
     *
     * @param l1 The first location, represented as a {@link Location} object.
     * @param l2 The second location, represented as a {@link Location} object.
     * @return The distance between the two locations in meters. If the distance cannot be calculated,
     *         returns -1.0.
     */
    public static double calculateDistance(Location l1, Location l2) {
        Map<String, Object> requestBodyMap = Map.of(
                "coordinates", List.of(
                        List.of(l1.getLongitude(), l1.getLatitude()),
                        List.of(l2.getLongitude(), l2.getLatitude())
                ),
                "format", "geojson"
        );

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            JsonNode jsonNode = objectMapper.readTree(response.body());
            return jsonNode.path("routes").get(0).path("summary").path("distance").asDouble();
        } catch (Exception e) {
            System.out.println("Something went wrong when calculating the distance!\nError: " + e.getMessage());
        }
        return -1.0;
    }
}