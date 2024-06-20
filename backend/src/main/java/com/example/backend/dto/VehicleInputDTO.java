package com.example.backend.dto;

public record VehicleInputDTO(Long id, String vehicleType, String vehicleName, Long seats, String wheelchair,
                              String startCoordinates, String endCoordinates) {
}
