package com.example.backend.repository;

import com.example.backend.model.VehicleDeploymentPlanning;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for VehicleDeploymentPlanning entities.
 * Extends ApiRepository and CustomApiRepository to provide standard and custom CRUD operations.
 */
@Repository
public interface VehicleDeploymentPlanningRepository extends ApiRepository<VehicleDeploymentPlanning>, CustomApiRepository<VehicleDeploymentPlanning> {

}
