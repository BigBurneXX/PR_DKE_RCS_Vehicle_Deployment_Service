package com.example.backend.dto;

import com.example.backend.model.Person;
import com.example.backend.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class VehicleDeploymentPlanningInputDTO {
    private Set<Person> persons;

    private Set<Vehicle> vehicles;
}
