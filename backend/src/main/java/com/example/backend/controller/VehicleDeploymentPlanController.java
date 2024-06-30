package com.example.backend.controller;

import com.example.backend.dto.VehicleDeploymentPlanOutputDTO;
import com.example.backend.service.VehicleDeploymentPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling vehicle deployment plan-related requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plans")
public class VehicleDeploymentPlanController {
    private final VehicleDeploymentPlanService planService;

    /**
     * Retrieves all vehicle deployment plans.
     *
     * @return a ResponseEntity containing a list of VehicleDeploymentPlanOutputDTOs.
     */
    @GetMapping
    public ResponseEntity<List<VehicleDeploymentPlanOutputDTO>> getAllPlans() {
        List<VehicleDeploymentPlanOutputDTO> plans = planService.getAllPlans();
        return plans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plans);
    }

    /**
     * Retrieves a vehicle deployment plan by its ID.
     *
     * @param id the ID of the vehicle deployment plan.
     * @return a ResponseEntity containing the VehicleDeploymentPlanOutputDTO or a not found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanOutputDTO> getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves vehicle deployment plans by vehicle ID.
     *
     * @param vehicleId the ID of the vehicle.
     * @return a ResponseEntity containing a list of VehicleDeploymentPlanOutputDTOs.
     */
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<VehicleDeploymentPlanOutputDTO>> getPlanByVehicle(@PathVariable Long vehicleId) {
        List<VehicleDeploymentPlanOutputDTO> plans = planService.getPlansByVehicle(vehicleId);
        return plans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plans);
    }

    /**
     * Retrieves all plans by a specific vehicle deployment planning ID.
     *
     * @param id the ID of the vehicle deployment planning.
     * @return a ResponseEntity containing a list of VehicleDeploymentPlanOutputDTOs or a not found status.
     */
    @GetMapping("/vehicleDeploymentPlanning/{id}")
    public ResponseEntity<List<VehicleDeploymentPlanOutputDTO>> getAllPlansByPlanning(@PathVariable Long id) {
        if(!planService.existsPlanning(id))
            return ResponseEntity.notFound().build();
        List<VehicleDeploymentPlanOutputDTO> plans = planService.getAllPlansByPlanning(id);
        return plans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plans);
    }
}
