package com.example.backend.controller;

import com.example.backend.model.VehicleDeploymentPlanning;
import com.example.backend.repository.VehicleDeploymentPlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-plannings")
public class VehicleDeploymentPlanningController {
    private final VehicleDeploymentPlanningRepository planningRepository;

    @GetMapping("/")
    public ResponseEntity<List<VehicleDeploymentPlanning>> getAllPlannings() {
        List<VehicleDeploymentPlanning> plannings = planningRepository.findAll();
        if (plannings.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(plannings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanning> getPlanningById(@PathVariable Long id) {
        Optional<VehicleDeploymentPlanning> planning = planningRepository.findById(id);
        return planning.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<VehicleDeploymentPlanning> createPlanning(@RequestBody VehicleDeploymentPlanning planning) {
        VehicleDeploymentPlanning savedPlanning = planningRepository.save(planning);
        URI location = URI.create("/vehicleplannings/" + savedPlanning.getId());
        return ResponseEntity.created(location).body(savedPlanning);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanning> updatePlanning(@PathVariable Long id, @RequestBody VehicleDeploymentPlanning planning) {
        if (planningRepository.existsById(id)) {
            planning.setId(id);
            VehicleDeploymentPlanning updatedPlanning = planningRepository.save(planning);
            return ResponseEntity.ok(updatedPlanning);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDeploymentPlanning> deletePlanning(@PathVariable Long id) {
        if (planningRepository.existsById(id)) {
            Optional<VehicleDeploymentPlanning> planning = planningRepository.findById(id);
            planningRepository.deleteById(id);
            return ResponseEntity.ok(planning.get());
        }
        return ResponseEntity.notFound().build();
    }
}
