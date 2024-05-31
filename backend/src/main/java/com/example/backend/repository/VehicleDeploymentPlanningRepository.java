package com.example.backend.repository;

import com.example.backend.model.VehicleDeploymentPlanning;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDeploymentPlanningRepository extends ApiRepository<VehicleDeploymentPlanning>, CustomApiRepository<VehicleDeploymentPlanning> {

}
