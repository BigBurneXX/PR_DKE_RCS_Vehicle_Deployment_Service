package com.example.backend.dto;

public record TripSheet_PersonInputDTO(Long id, Long personId, LocationDTO startLocation, LocationDTO endLocation,
                                       boolean hasWheelchair) {
}
