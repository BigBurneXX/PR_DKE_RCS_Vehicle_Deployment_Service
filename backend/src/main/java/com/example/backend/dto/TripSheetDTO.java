package com.example.backend.dto;

import lombok.*;

import java.util.List;

public record TripSheetInputDTO(VehicleDeploymentPlanDTO vehicleDeploymentPlan, Location[] locations) {

}
