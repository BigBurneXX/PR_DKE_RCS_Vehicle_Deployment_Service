package com.example.backend.dto;

import java.util.Set;

public record VehicleDeploymentPlanningOutputDTO(Long id, Set<PersonOutputDTO> persons, Set<VehicleOutputDTO> vehicles,
                                                 Set<VehicleDeploymentPlanOutputDTO> plans) {
}
