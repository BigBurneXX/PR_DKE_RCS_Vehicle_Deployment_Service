package com.example.backend.controller;

import com.example.backend.model.VehicleDeploymentService;
import com.example.backend.repository.VehicleDeploymentServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle-deployment-services")
public class VehicleDeploymentServiceController {

    @Autowired
    private VehicleDeploymentServiceRepository serviceRepository;

    @GetMapping("/")
    public ResponseEntity<List<VehicleDeploymentService>> getAllServices() {
        List<VehicleDeploymentService> vehicleDeploymentServices = serviceRepository.findAll();
        if(vehicleDeploymentServices.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(vehicleDeploymentServices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDeploymentService> getServiceById(@PathVariable Long id){
        Optional<VehicleDeploymentService> vehicleDeploymentService = serviceRepository.findById(id);
        return vehicleDeploymentService.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<VehicleDeploymentService> createService(@RequestBody VehicleDeploymentService service) {
        VehicleDeploymentService savedService = serviceRepository.save(service);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedService.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDeploymentService> updateFullService(@PathVariable Long id, @RequestBody VehicleDeploymentService service) {
        Optional<VehicleDeploymentService> vehicleDeploymentService = serviceRepository.findById(id);
        if(vehicleDeploymentService.isPresent()) {
            VehicleDeploymentService updatedService =  serviceRepository.save(service);
            return ResponseEntity.ok(updatedService);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDeploymentService> updateService(@PathVariable Long id, @RequestBody VehicleDeploymentService service) {
        Optional<VehicleDeploymentService> vehicleDeploymentService = serviceRepository.findById(id);
        if(vehicleDeploymentService.isPresent()) {
            VehicleDeploymentService updatedService =  serviceRepository.save(service);
            return ResponseEntity.ok(updatedService);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDeploymentService> deleteService(@PathVariable Long id) {
        if(serviceRepository.existsById(id)) {
            Optional<VehicleDeploymentService> service = serviceRepository.findById(id);
            serviceRepository.deleteById(id);
            return ResponseEntity.ok(service.get());
        }
        return ResponseEntity.notFound().build();
    }
}