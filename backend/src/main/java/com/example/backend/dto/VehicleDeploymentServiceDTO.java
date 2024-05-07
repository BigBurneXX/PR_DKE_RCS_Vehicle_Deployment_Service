package com.example.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDeploymentServiceDTO {

    private Long id;

    private String serviceName;

    private String description;

    private List<VehicleDeploymentPlanDTO> deploymentPlans;
}

