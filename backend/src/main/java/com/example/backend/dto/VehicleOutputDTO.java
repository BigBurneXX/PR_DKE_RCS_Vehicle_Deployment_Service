package com.example.backend.dto;

import java.util.Date;

public record VehicleOutputDTO(Long id, Date creationDate, Date lastModifiedDate, int seats, boolean hasWheelchair,
                               LocationDTO startLocation, LocationDTO endLocation) {
}
