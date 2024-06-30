package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * Data Transfer Object for vehicle deployment plan output.
 */
@Data
@NoArgsConstructor
public class VehicleDeploymentPlanOutputDTO {
    private Long id;
    private String name;
    private Date creationDate;
    private Date lastModifiedDate;
    private Long version;
    private VehicleOutputDTO vehicle;
    private Set<PersonOutputDTO> persons;
    private Set<LocationDTO> locations;
    private Set<Long> tripSheetIds;
    private Long planningId;
}
