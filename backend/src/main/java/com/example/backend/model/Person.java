package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private Long startAddressId;

    private Long targetAddressId;

    private boolean hasWheelChair;

    @ManyToOne
    @JoinColumn(name = "vehicle_deployment_planning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
