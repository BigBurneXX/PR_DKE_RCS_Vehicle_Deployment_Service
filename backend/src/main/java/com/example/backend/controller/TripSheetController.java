package com.example.backend.controller;

import com.example.backend.model.TripSheet;
import com.example.backend.model.VehicleDeploymentPlan;
import com.example.backend.repository.TripSheetRepository;
import com.example.backend.repository.VehicleDeploymentPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip-sheets")
public class TripSheetController {
    private final TripSheetRepository tripSheetRepository;
    private final VehicleDeploymentPlanRepository vehicleDeploymentPlanRepository;

    @GetMapping
    public ResponseEntity<List<TripSheet>> getAllTripSheets() {
        List<TripSheet> tripSheets = tripSheetRepository.findByIsActiveTrue();
        return tripSheets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tripSheets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripSheet> getTripSheetById(@PathVariable Long id) {
        Optional<TripSheet> tripSheets = tripSheetRepository.findByIdAndIsActiveTrue(id);
        return tripSheets.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicleDeploymentPlan/{vehicleDeploymentPlanId}")
    public ResponseEntity<List<TripSheet>> getTripSheetsByVehicleDeploymentPlan(@PathVariable Long vehicleDeploymentPlanId) {
        Optional<VehicleDeploymentPlan> vehicleDeploymentPlan = vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentPlanId);
        if(vehicleDeploymentPlan.isPresent()) {
            List<TripSheet> tripSheets = tripSheetRepository.findByVehicleDeploymentPlanAndIsActiveTrue(vehicleDeploymentPlan.get());
            return tripSheets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tripSheets);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/vehicleDeploymentPlan/{vehicleDeploymentPlanId}")
    public ResponseEntity<TripSheet> createTripSheet(@PathVariable Long vehicleDeploymentPlanId) {
        Optional<VehicleDeploymentPlan> vehicleDeploymentPlan = vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentPlanId);
        if(vehicleDeploymentPlan.isPresent()) {
            TripSheet tripSheet = new TripSheet();
            tripSheet.setVehicleDeploymentPlan(vehicleDeploymentPlan.get());
            tripSheetRepository.save(tripSheet);
            return ResponseEntity.ok(tripSheet);
        }
        return ResponseEntity.notFound().build();
    }

    //TODO: Implement full post functionality
/*
    @PatchMapping("/{id}")
    public ResponseEntity<TripSheet> updateTripSheet(@PathVariable Long id, @RequestBody Address address) {
        Optional<TripSheet> possibleTripSheet = tripSheetRepository.findByIdAndIsActiveTrue(id);
        if(possibleTripSheet.isPresent()) {
            TripSheet tripSheet = possibleTripSheet.get();
            tripSheet.addAddress(address);
            tripSheetRepository.save(tripSheet);
            return ResponseEntity.ok(tripSheet);
        }
        return ResponseEntity.notFound().build();
    }
*/
    @DeleteMapping("/{id}")
    public ResponseEntity<TripSheet> deleteTripSheet(@PathVariable Long id) {
        Optional<TripSheet> tripSheet = tripSheetRepository.findByIdAndIsActiveTrue(id);
        if(tripSheet.isPresent()) {
            tripSheetRepository.softDelete(id, TripSheet.class);
            return ResponseEntity.ok(tripSheet.get());
        }
        return ResponseEntity.notFound().build();
    }
}
