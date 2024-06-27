package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    // as the Data Base Systems needs the address id it will be saved here
    private Long addressId = null;

    //Temporarily used for LocationSolve
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getDistanceTo(Location location) {
        double deltaLat = location.getLatitude() - this.latitude;
        double deltaLon = location.getLongitude() - this.longitude;
        return Math.sqrt(deltaLat * deltaLat + deltaLon * deltaLon);
    }
}
