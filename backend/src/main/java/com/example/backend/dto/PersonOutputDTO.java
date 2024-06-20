package com.example.backend.dto;

import java.util.Date;

public record PersonOutputDTO(Long id, Date creationDate, Date lastModifiedDate, LocationDTO startLocation,
                              LocationDTO endLocation, boolean hasWheelchair) {
}
