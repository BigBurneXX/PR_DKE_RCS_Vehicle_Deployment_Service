package com.example.backend.repository;

import com.example.backend.model.TripSheet;
import com.example.backend.model.VehicleDeploymentPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for TripSheet entities.
 * Extends ApiRepository and CustomApiRepository to provide standard and custom CRUD operations.
 */
@Repository
public interface TripSheetRepository extends ApiRepository<TripSheet>, CustomApiRepository<TripSheet> {
    /**
     * Finds all active TripSheet entities for a given VehicleDeploymentPlan.
     *
     * @param vehicleDeploymentPlan the vehicle deployment plan
     * @return a list of active TripSheet entities
     */
    List<TripSheet> findByVehicleDeploymentPlanAndIsActiveTrue(VehicleDeploymentPlan vehicleDeploymentPlan);
}
