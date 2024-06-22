package com.example.backend.controller;

import com.example.backend.dto.LocationDTO;
import com.example.backend.dto.TripSheetOutputDTO;
import com.example.backend.model.TripSheet;
import com.example.backend.repository.TripSheetRepository;
import com.example.backend.service.TripSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip-sheets")
public class TripSheetController {
    private final TripSheetRepository tripSheetRepository;
    private final TripSheetService tripSheetService;

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
    public ResponseEntity<List<TripSheetOutputDTO>> getTripSheetsByVehicleDeploymentPlan(@PathVariable Long vehicleDeploymentPlanId) {
        List<TripSheetOutputDTO> tripSheets = tripSheetService.getByPlanId(vehicleDeploymentPlanId);
        if(tripSheets == null)
            return ResponseEntity.notFound().build();
        return tripSheets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tripSheets);
    }

    @PostMapping("/vehicleDeploymentPlan/{vehicleDeploymentPlanId}")
    public ResponseEntity<TripSheetOutputDTO> createTripSheet(@PathVariable Long vehicleDeploymentPlanId, @RequestBody Set<LocationDTO> locations) {
        TripSheetOutputDTO tripSheet = tripSheetService.createTripSheet(vehicleDeploymentPlanId, locations);
        return tripSheet == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(tripSheet);
    }

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
