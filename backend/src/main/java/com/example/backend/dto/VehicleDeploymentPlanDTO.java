package com.example.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDeploymentPlanDTO {
    private Long id;

    private Long vehicleId;

    private List<AddressDTO> addresses;
}
