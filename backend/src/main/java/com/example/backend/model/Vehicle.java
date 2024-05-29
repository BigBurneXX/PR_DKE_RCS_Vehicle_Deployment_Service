package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleType;

    private String vehicleName;

    private int seats;

    private boolean wheelchair;

    @ManyToMany
    @JoinTable(
            name = "Vehicle_to_VehicleDeploymentPlannings",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicleDeploymentPlanning_id")
    )
    private Set<VehicleDeploymentPlanning> vehicleDeploymentPlannings = new HashSet<>();
}