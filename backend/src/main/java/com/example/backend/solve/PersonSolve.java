package com.example.backend.solve;

import com.example.backend.model.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@Setter
@Getter
@NoArgsConstructor
@PlanningEntity
public class PersonSolve {
    private String id;
    private Location startLocation;
    private Location endLocation;
    private boolean hasWheelchair;
    @PlanningVariable(valueRangeProviderRefs = "vehicleRange")
    private VehicleSolve assignedVehicle;

    public PersonSolve(String id, Location startLocation, Location endLocation, boolean hasWheelchair) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.hasWheelchair = hasWheelchair;
    }
}

