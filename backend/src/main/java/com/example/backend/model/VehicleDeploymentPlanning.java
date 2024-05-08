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
public class VehicleDeploymentPlanning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "vehicleDeploymentPlanning", cascade = CascadeType.ALL)
    private List<Person> persons;

    @OneToMany(mappedBy = "vehicleDeploymentPlanning", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "vehicleDeploymentPlanning", cascade = CascadeType.ALL)
    private List<VehicleDeploymentPlan> plans;

    @ManyToOne
    @JoinColumn(name = "vehicle_deployment_service_id")
    private VehicleDeploymentService vehicleDeploymentService;
}
