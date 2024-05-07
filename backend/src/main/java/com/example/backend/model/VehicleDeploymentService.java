package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleDeploymentService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String description;

    @OneToMany(mappedBy = "vehicleDeploymentService", cascade = CascadeType.ALL)
    private List<VehicleDeploymentPlanning> vehicleDeploymentPlannings;
}
