package com.example.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vehicle extends MetaData {
    private Long vehicleId;
    private String name;
    private String type;
    private int seats;
    private boolean canCarryWheelchair;
    @ManyToOne
    private Location startLocation;
    @ManyToOne
    private Location endLocation;
}