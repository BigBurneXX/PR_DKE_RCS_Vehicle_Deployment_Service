package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LocationDTO {
    private Long id;
    private double latitude;
    private double longitude;
}
