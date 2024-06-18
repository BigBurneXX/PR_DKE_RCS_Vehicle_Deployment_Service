package com.example.backend.controller;

import com.example.backend.model.VehicleDeploymentPlan;
import com.example.backend.model.VehicleDeploymentPlanning;
import com.example.backend.repository.VehicleDeploymentPlanRepository;
import com.example.backend.repository.VehicleDeploymentPlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plans")
public class VehicleDeploymentPlanController {
    private final VehicleDeploymentPlanRepository planRepository;
    private final VehicleDeploymentPlanningRepository planningRepository;

    @GetMapping
    public ResponseEntity<List<VehicleDeploymentPlan>> getAllPlans() {
        List<VehicleDeploymentPlan> plans = planRepository.findByIsActiveTrue();
        return plans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlan> getPlanById(@PathVariable Long id) {
        Optional<VehicleDeploymentPlan> plan = planRepository.findByIdAndIsActiveTrue(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<VehicleDeploymentPlan> getPlanByVehicle(@PathVariable Long vehicleId) {
        Optional<VehicleDeploymentPlan> plan = planRepository.findByVehicleAndIsActiveTrue(vehicleId);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicleDeploymentPlanning/{id}")
    public ResponseEntity<List<VehicleDeploymentPlan>> getAllPlansByPlanning(@PathVariable Long id) {
        Optional<VehicleDeploymentPlanning> vehicleDeploymentPlanning = planningRepository.findByIdAndIsActiveTrue(id);
        if(vehicleDeploymentPlanning.isPresent()) {
            List<VehicleDeploymentPlan> plans = planRepository.findByVehicleDeploymentPlanningAndIsActiveTrue(vehicleDeploymentPlanning.get());
            return plans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plans);
        }
        return ResponseEntity.notFound().build();
    }
}
