package com.example.backend.controller;

import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningOutputDTO;
import com.example.backend.model.VehicleDeploymentPlanning;
import com.example.backend.repository.VehicleDeploymentPlanningRepository;
import com.example.backend.service.VehicleRoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plannings")
public class VehicleDeploymentPlanningController {
    private final VehicleDeploymentPlanningRepository planningRepository;
    private final VehicleRoutingService vehicleRoutingService;

    @GetMapping
    public ResponseEntity<List<VehicleDeploymentPlanning>> getAllPlannings() {
        List<VehicleDeploymentPlanning> plannings = planningRepository.findByIsActiveTrue();
        return plannings.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plannings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanning> getPlanningById(@PathVariable Long id) {
        Optional<VehicleDeploymentPlanning> planning = planningRepository.findById(id);
        return planning.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VehicleDeploymentPlanningOutputDTO> createPlanning(@RequestBody VehicleDeploymentPlanningInputDTO planning) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleRoutingService.solve(planning));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanning> deletePlanning(@PathVariable Long id) {
        if (planningRepository.existsById(id)) {
            Optional<VehicleDeploymentPlanning> planning = planningRepository.findById(id);
            planningRepository.softDelete(id, VehicleDeploymentPlanning.class);
            if(planning.isPresent())
                return ResponseEntity.ok(planning.get());
        }
        return ResponseEntity.notFound().build();
    }
}
