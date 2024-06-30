package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data Transfer Object for vehicle output.
 */
@Data
@NoArgsConstructor
public class VehicleOutputDTO {
    private Long id;
    private String name;
    private Date creationDate;
    private Date lastModifiedDate;
    private Long version;
    private Long vehicleId;
    private String type;
    private int seats;
    private boolean canCarryWheelchair;
    private LocationDTO startLocation;
    private LocationDTO endLocation;
}
