package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String longitude;
    private String latitude;

    // as the Data Base Systems needs the address id it will be saved here
    private Long addressId = null;

    @ManyToOne
    @JoinColumn(name = "vehicleDeploymentPlanning_id")
    private VehicleDeploymentPlanning vehicleDeploymentPlanning;
}
