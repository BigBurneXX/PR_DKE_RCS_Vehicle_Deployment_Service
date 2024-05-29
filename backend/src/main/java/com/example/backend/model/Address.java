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
            name = "Address_to_VehicleDeploymentPlans",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicleDeploymentPlan_id")
    )
    private Set<VehicleDeploymentPlan> vehicleDeploymentPlans = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Address_to_TripSheet",
            joinColumns = @JoinColumn(name = "addresss_id"),
            inverseJoinColumns = @JoinColumn(name = "tripSheet_id")
    )
    private Set<TripSheet> tripSheets = new HashSet<>();
}
