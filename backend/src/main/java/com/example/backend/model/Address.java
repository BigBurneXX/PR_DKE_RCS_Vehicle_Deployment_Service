package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String houseNo;

    private Long townId;

    private String coordinates;

    @ManyToOne
    @JoinColumn(name = "vehicle_deployment_plan_id")
    private VehicleDeploymentPlan vehicleDeploymentPlan;
}
