package com.example.backend.repository;

import com.example.backend.model.VehicleDeploymentPlanning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDeploymentPlanningRepository extends JpaRepository<VehicleDeploymentPlanning, Long> {
}
