package com.example.backend.controller;

import com.example.backend.dto.LocationDTO;
import com.example.backend.dto.TripSheetOutputDTO;
import com.example.backend.service.TripSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip-sheets")
public class TripSheetController {
    private final TripSheetService tripSheetService;

    @GetMapping
    public ResponseEntity<List<TripSheetOutputDTO>> getAllTripSheets() {
        List<TripSheetOutputDTO> tripSheets = tripSheetService.getAllTripSheets();
        return tripSheets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tripSheets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripSheetOutputDTO> getTripSheetById(@PathVariable Long id) {
        return tripSheetService.getTripSheetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicleDeploymentPlan/{vehicleDeploymentPlanId}")
    public ResponseEntity<List<TripSheetOutputDTO>> getTripSheetsByVehicleDeploymentPlan(@PathVariable Long vehicleDeploymentPlanId) {
        List<TripSheetOutputDTO> tripSheets = tripSheetService.getByPlanId(vehicleDeploymentPlanId);
        return tripSheets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tripSheets);
    }

    @PostMapping("/vehicleDeploymentPlan/{vehicleDeploymentPlanId}")
    public ResponseEntity<TripSheetOutputDTO> createTripSheet(@PathVariable Long vehicleDeploymentPlanId, @RequestBody Set<LocationDTO> locations) {
        if(locations == null || locations.isEmpty() || !tripSheetService.existsPlan(vehicleDeploymentPlanId))
            return ResponseEntity.badRequest().build();
        TripSheetOutputDTO tripSheet = tripSheetService.createTripSheet(vehicleDeploymentPlanId, locations);
        return tripSheet == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(tripSheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripSheet(@PathVariable Long id) {
        if(!tripSheetService.existsTripSheet(id))
            return ResponseEntity.notFound().build();
        tripSheetService.deleteTripSheet(id);
        return ResponseEntity.ok().build();
    }
}
