package com.example.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDeploymentPlanningDTO {
    private Long id;

    private List<PersonDTO> persons;

    private List<VehicleDTO> vehicles;

    private List<VehicleDeploymentPlanDTO> plans;
}
