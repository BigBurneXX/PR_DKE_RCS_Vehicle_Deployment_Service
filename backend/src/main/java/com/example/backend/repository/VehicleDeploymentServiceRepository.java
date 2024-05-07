package com.example.backend.repository;

import com.example.backend.model.VehicleDeploymentService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDeploymentServiceRepository extends JpaRepository<VehicleDeploymentService, Long> {
}
