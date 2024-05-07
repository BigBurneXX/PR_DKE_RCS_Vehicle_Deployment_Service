package com.example.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TripSheetDTO {
    private Long id;

    private Long vehicleDeploymentPlanId;

    private List<Boolean> visitStatus;
}
