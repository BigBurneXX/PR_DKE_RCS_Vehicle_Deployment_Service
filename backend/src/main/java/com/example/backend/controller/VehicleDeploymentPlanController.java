package com.example.backend.controller;

import com.example.backend.model.VehicleDeploymentPlan;
import com.example.backend.repository.VehicleDeploymentPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plans")
public class VehicleDeploymentPlanController {
    private final VehicleDeploymentPlanRepository planRepository;

    @GetMapping("/")
    public ResponseEntity<List<VehicleDeploymentPlan>> getAllPlans() {
        List<VehicleDeploymentPlan> plans = planRepository.findAll();
        if (plans.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlan> getPlanById(@PathVariable Long id) {
        Optional<VehicleDeploymentPlan> plan = planRepository.findById(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<VehicleDeploymentPlan> createPlan(@RequestBody VehicleDeploymentPlan plan) {
        VehicleDeploymentPlan savedPlan = planRepository.save(plan);
        URI location = URI.create("/vehicleplans/" + savedPlan.getId());
        return ResponseEntity.created(location).body(savedPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlan> updatePlan(@PathVariable Long id, @RequestBody VehicleDeploymentPlan plan) {
        if (planRepository.existsById(id)) {
            plan.setId(id);
            VehicleDeploymentPlan updatedPlan = planRepository.save(plan);
            return ResponseEntity.ok(updatedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlan> deletePlan(@PathVariable Long id) {
        if (planRepository.existsById(id)) {
            Optional<VehicleDeploymentPlan> plan = planRepository.findById(id);
            planRepository.deleteById(id);
            return ResponseEntity.ok(plan.get());
        }
        return ResponseEntity.notFound().build();
    }
}
