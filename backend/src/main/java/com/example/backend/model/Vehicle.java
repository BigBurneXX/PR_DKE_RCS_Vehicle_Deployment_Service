package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "VehicleDto")
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
            name = "vehicle_vehicleDeploymentPlanning",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicleDeploymentPlanning_id")
    )
    private Set<VehicleDeploymentPlanning> vehicleDeploymentPlannings = new HashSet<>();

    @OneToMany(mappedBy = "vehicle")
    private Set<VehicleDeploymentPlan> vehicleDeploymentPlan = new HashSet<>();
}