package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String houseNo;

    private Long townId;

    private String coordinates;

    @ManyToMany
    @JoinTable(
            name = "address_vehicleDeploymentPlan",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicleDeploymentPlan_id")
    )
    private Set<VehicleDeploymentPlan> vehicleDeploymentPlan = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "address_tripSheet",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "tripSheet_id")
    )
    private Set<TripSheet> tripSheet = new HashSet<>();
}
