package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class TripSheetOutputDTO {
    private Long id;
    private String name;
    private Date creationDate;
    private Date lastModifiedDate;
    private Long version;
    private VehicleOutputDTO vehicle;
    private Set<PersonOutputDTO> persons;
    private List<LocationDTO> locations;
    private Long vehicleDeploymentPlanId;
}
