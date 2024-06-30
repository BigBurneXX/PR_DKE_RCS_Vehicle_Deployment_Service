package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * Data Transfer Object for vehicle deployment planning output.
 */
@Data
@NoArgsConstructor
public class VehicleDeploymentPlanningOutputDTO {
    private Long id;
    private String name;
    private Date creationDate;
    private Date lastModifiedDate;
    private Long version;
    private Set<VehicleOutputDTO> vehicles;
    private Set<PersonOutputDTO> persons;
    private Set<VehicleDeploymentPlanOutputDTO> plans;
}
