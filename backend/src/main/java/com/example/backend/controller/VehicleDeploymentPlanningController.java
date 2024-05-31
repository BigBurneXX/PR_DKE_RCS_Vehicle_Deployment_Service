package com.example.backend.controller;

import com.example.backend.model.VehicleDeploymentPlanning;
import com.example.backend.repository.VehicleDeploymentPlanningRepository;
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

    @GetMapping("/")
    public ResponseEntity<List<VehicleDeploymentPlanning>> getAllPlannings() {
        List<VehicleDeploymentPlanning> plannings = planningRepository.findByIsActiveTrue();
        return plannings.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plannings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanning> getPlanningById(@PathVariable Long id) {
        Optional<VehicleDeploymentPlanning> planning = planningRepository.findById(id);
        return planning.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO: a post request should create a number of vdp with the help of open route service
    @PostMapping("/")
    public ResponseEntity<VehicleDeploymentPlanning> createPlanning(@RequestBody VehicleDeploymentPlanning planning) {
        VehicleDeploymentPlanning savedPlanning = planningRepository.save(planning);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlanning);
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
