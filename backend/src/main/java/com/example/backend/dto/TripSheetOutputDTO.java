package com.example.backend.dto;
public record TripSheetOutputDTO(Long id, VehicleDeploymentPlanDTO vehicleDeploymentPlan, Location[] locations) {
}
