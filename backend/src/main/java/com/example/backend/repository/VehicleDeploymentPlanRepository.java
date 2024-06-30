package com.example.backend.repository;

import com.example.backend.model.VehicleDeploymentPlan;
import com.example.backend.model.VehicleDeploymentPlanning;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for VehicleDeploymentPlan entities.
 * Extends ApiRepository and CustomApiRepository to provide standard and custom CRUD operations.
 */
@Repository
public interface VehicleDeploymentPlanRepository extends ApiRepository<VehicleDeploymentPlan>, CustomApiRepository<VehicleDeploymentPlan> {
    /**
     * Finds all active VehicleDeploymentPlan entities for a given VehicleDeploymentPlanning.
     *
     * @param vehicleDeploymentPlanning the vehicle deployment planning
     * @return a list of active VehicleDeploymentPlan entities
     */
    List<VehicleDeploymentPlan> findByVehicleDeploymentPlanningAndIsActiveTrue(VehicleDeploymentPlanning vehicleDeploymentPlanning);
    /**
     * Finds all active VehicleDeploymentPlan entities for a given vehicle ID.
     *
     * @param vehicleId the vehicle ID
     * @return a list of active VehicleDeploymentPlan entities
     */
    List<VehicleDeploymentPlan> findByVehicleIdAndIsActiveTrue(Long vehicleId);
}
