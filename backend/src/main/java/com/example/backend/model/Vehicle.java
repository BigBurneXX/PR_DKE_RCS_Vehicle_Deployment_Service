package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
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

    @ManyToOne
    @JoinColumn(name = "vehicle_deployment_planning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}