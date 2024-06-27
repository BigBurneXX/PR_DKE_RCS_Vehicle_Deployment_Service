package com.example.backend.repository;

import com.example.backend.model.VehicleDeploymentPlan;
import com.example.backend.model.VehicleDeploymentPlanning;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleDeploymentPlanRepository extends ApiRepository<VehicleDeploymentPlan>, CustomApiRepository<VehicleDeploymentPlan> {
    List<VehicleDeploymentPlan> findByVehicleDeploymentPlanningAndIsActiveTrue(VehicleDeploymentPlanning vehicleDeploymentPlanning);
    List<VehicleDeploymentPlan> findByVehicleIdAndIsActiveTrue(Long vehicleId);
}
