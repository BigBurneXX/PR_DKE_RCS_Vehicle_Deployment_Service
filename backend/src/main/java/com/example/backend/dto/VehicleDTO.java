package com.example.backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long id;

    private String vehicleType;

    private String vehicleName;

    private int seats;

    private boolean wheelchair;
}
