package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Address")
public class Address extends MetaData {

    private String street;

    private String houseNo;

    private Long townId;

    private String coordinates;

    @ManyToOne
    @JoinColumn(name = "vehicle_deployment_plan_id")
    private VehicleDeploymentPlan vehicleDeploymentPlan;
}
