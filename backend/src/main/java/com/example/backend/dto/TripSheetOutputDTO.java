package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TripSheetOutputDTO {
    private Long id;
    private Date creationDate;
    private Date lastModifiedDate;
    private Long version;
    private VehicleDeploymentPlanOutputDTO vehicleDeploymentPlan;
    private LocationDTO[] locations;
}
