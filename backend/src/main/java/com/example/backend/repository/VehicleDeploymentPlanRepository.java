package com.example.backend.repository;

import com.example.backend.model.VehicleDeploymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDeploymentPlanRepository extends JpaRepository<VehicleDeploymentPlan, Long> {
}
