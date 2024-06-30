package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for locations.
 */
@Data
@NoArgsConstructor
public class LocationDTO {
    private Long id;
    private double latitude;
    private double longitude;
    private Long addressId;
}
