package com.example.backend.dto;
public record TripSheetOutputDTO(Long id, VehicleDeploymentPlanOutputDTO vehicleDeploymentPlan, LocationDTO[] locations) {
}
