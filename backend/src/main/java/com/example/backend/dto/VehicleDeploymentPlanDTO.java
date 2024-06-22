package com.example.backend.dto;

public record VehicleDeploymentPlanDTO (Long id, VehicleOutputDTO vehicle, LocationDTO[] locations) {
}
