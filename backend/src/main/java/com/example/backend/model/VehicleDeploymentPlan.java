package com.example.backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "VehicleDeploymentPlan")
public class VehicleDeploymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;

    @OneToMany(mappedBy = "vehicleDeploymentPlan", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @ManyToOne
    @JoinColumn(name = "vehicle_deployment_planning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
