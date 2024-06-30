package com.example.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a vehicle with metadata, including start and end locations, and attributes like type and seats.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vehicle extends MetaData {
    private Long vehicleId;
    private String type;
    private int seats;
    private boolean canCarryWheelchair;
    @ManyToOne
    private Location startLocation;
    @ManyToOne
    private Location endLocation;
}