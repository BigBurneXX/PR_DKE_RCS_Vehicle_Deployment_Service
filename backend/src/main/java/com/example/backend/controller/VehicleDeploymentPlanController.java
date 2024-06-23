package com.example.backend.controller;

import com.example.backend.dto.VehicleDeploymentPlanOutputDTO;
import com.example.backend.service.VehicleDeploymentPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plans")
public class VehicleDeploymentPlanController {
    private final VehicleDeploymentPlanService planService;

    @GetMapping
    public ResponseEntity<List<VehicleDeploymentPlanOutputDTO>> getAllPlans() {
        List<VehicleDeploymentPlanOutputDTO> plans = planService.getAllPlans();
        return plans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanOutputDTO> getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<VehicleDeploymentPlanOutputDTO> getPlanByVehicle(@PathVariable Long vehicleId) {
        return planService.getPlanByVehicle(vehicleId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicleDeploymentPlanning/{id}")
    public ResponseEntity<List<VehicleDeploymentPlanOutputDTO>> getAllPlansByPlanning(@PathVariable Long id) {
        if(!planService.existsPlanning(id))
            return ResponseEntity.notFound().build();
        List<VehicleDeploymentPlanOutputDTO> plans = planService.getAllPlansByPlanning(id);
        return plans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plans);
    }
}
