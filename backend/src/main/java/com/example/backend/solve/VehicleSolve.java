package com.example.backend.solve;

import com.example.backend.model.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSolve {
    private String id;
    private int capacity;
    private Location startLocation;
    private Location endLocation;
    private boolean canCarryWheelchair;
}
