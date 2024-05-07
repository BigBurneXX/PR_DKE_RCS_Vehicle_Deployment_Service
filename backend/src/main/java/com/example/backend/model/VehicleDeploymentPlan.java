package com.example.backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleDeploymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;

    @OneToMany(mappedBy = "vehicleDeploymentPlan", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
