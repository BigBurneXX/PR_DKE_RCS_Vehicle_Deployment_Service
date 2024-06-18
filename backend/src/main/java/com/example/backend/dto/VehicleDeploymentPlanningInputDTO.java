package com.example.backend.dto;

import com.example.backend.model.Person;
import com.example.backend.model.Vehicle;

import java.util.Set;

public record VehicleDeploymentPlanningInputDTO (Set<Person> persons, Set<Vehicle> vehicles) {
}
