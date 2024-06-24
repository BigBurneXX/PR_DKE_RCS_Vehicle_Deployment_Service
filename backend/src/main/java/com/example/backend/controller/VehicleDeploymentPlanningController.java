package com.example.backend.controller;

import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningOutputDTO;
import com.example.backend.service.VehicleDeploymentPlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plannings")
public class VehicleDeploymentPlanningController {
    private final VehicleDeploymentPlanningService planningService;

    @GetMapping
    public ResponseEntity<List<VehicleDeploymentPlanningOutputDTO>> getAllPlannings() {
        List<VehicleDeploymentPlanningOutputDTO> plannings = planningService.getAllPlannings();
        return plannings.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plannings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanningOutputDTO> getPlanningById(@PathVariable Long id) {
        return planningService.getPlanningById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VehicleDeploymentPlanningOutputDTO> createPlanning(@RequestBody VehicleDeploymentPlanningInputDTO planning) {
        if (planning == null || planning.addresses().isEmpty() || planning.persons().isEmpty() || planning.vehicles().isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(planningService.createPlanning(planning));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanningOutputDTO> deletePlanning(@PathVariable Long id) {
        if(!planningService.existsPlanning(id))
            return ResponseEntity.notFound().build();
        planningService.deletePlanning(id);
        return ResponseEntity.noContent().build();
    }
}
