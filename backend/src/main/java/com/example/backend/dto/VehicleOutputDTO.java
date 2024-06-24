package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VehicleOutputDTO {
    private Long id;
    private Date creationDate;
    private Date lastModifiedDate;
    private Long version;
    private Long vehicleId;
    private int seats;
    private boolean canCarryWheelchair;
    private LocationDTO startLocation;
    private LocationDTO endLocation;
}
