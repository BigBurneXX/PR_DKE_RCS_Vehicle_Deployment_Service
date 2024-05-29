package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends MetaData{
    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private Long startAddressId;

    private Long targetAddressId;

    private boolean hasWheelChair;

    @ManyToMany
    @JoinTable(
            name = "Person_to_VehicleDeploymentPlanning",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicleDeploymentPlanning_id")
    )
    private Set<VehicleDeploymentPlanning> vehicleDeploymentPlannings = new HashSet<>();
}
