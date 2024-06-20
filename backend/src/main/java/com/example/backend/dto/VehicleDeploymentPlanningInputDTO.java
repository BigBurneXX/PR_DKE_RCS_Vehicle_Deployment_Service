package com.example.backend.dto;

import java.util.Set;

public record VehicleDeploymentPlanningInputDTO (Set<PersonInputDTO> persons, Set<VehicleInputDTO> vehicles,
                                                 Set<AddressDTO> addresses) {
}
