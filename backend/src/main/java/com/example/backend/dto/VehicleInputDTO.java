package com.example.backend.dto;

/**
 * Data Transfer Object for vehicle input.
 */
public record VehicleInputDTO(Long id, String vehicle_type, String vehicle_name, Long seats, String wheelchair,
                              String start_coordinates, String end_coordinates) {
}
