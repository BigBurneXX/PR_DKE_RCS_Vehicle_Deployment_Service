package com.example.backend.dto;

import java.util.Set;

/**
 * Data Transfer Object for vehicle deployment planning input.
 */
public record VehicleDeploymentPlanningInputDTO (String name, Set<PersonInputDTO> persons,
                                                 Set<VehicleInputDTO> vehicles, Set<AddressInputDTO> addresses) {
}
