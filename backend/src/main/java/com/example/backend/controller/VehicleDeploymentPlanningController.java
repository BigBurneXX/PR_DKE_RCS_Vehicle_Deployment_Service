package com.example.backend.controller;

import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningOutputDTO;
import com.example.backend.service.VehicleDeploymentPlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling vehicle deployment planning-related requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plannings")
public class VehicleDeploymentPlanningController {
    private final VehicleDeploymentPlanningService planningService;

    /**
     * Retrieves all vehicle deployment plannings.
     *
     * @return a ResponseEntity containing a list of VehicleDeploymentPlanningOutputDTOs.
     */
    @GetMapping
    public ResponseEntity<List<VehicleDeploymentPlanningOutputDTO>> getAllPlannings() {
        List<VehicleDeploymentPlanningOutputDTO> plannings = planningService.getAllPlannings();
        return plannings.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plannings);
    }

    /**
     * Retrieves a vehicle deployment planning by its ID.
     *
     * @param id the ID of the vehicle deployment planning.
     * @return a ResponseEntity containing the VehicleDeploymentPlanningOutputDTO or a not found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanningOutputDTO> getPlanningById(@PathVariable Long id) {
        return planningService.getPlanningById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new vehicle deployment planning.
     *
     * @param planning the VehicleDeploymentPlanningInputDTO containing the planning details.
     * @return a ResponseEntity containing the created VehicleDeploymentPlanningOutputDTO or a bad request status.
     */
    @PostMapping
    public ResponseEntity<VehicleDeploymentPlanningOutputDTO> createPlanning(@RequestBody VehicleDeploymentPlanningInputDTO planning) {
        if (planning == null || planning.addresses().isEmpty() || planning.persons().isEmpty() || planning.vehicles().isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(planningService.createPlanning(planning));
    }

    /**
     * Deletes a vehicle deployment planning by its ID.
     *
     * @param id the ID of the vehicle deployment planning.
     * @return a ResponseEntity with the deletion status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanningOutputDTO> deletePlanning(@PathVariable Long id) {
        if(!planningService.existsPlanning(id))
            return ResponseEntity.notFound().build();
        planningService.deletePlanning(id);
        return ResponseEntity.noContent().build();
    }
}
