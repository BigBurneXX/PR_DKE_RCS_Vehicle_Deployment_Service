package com.example.backend.dto;

import java.util.Date;
import java.util.Set;

public record VehicleDeploymentPlanOutputDTO(Long id, Date creationDate, Date lastModifiedDate, VehicleOutputDTO vehicle,
                                             Set<PersonOutputDTO> persons, Set<LocationDTO> locations,
                                             Set<Long> tripSheetIds) {
}
