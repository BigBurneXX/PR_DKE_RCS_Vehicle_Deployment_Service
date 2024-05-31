package com.example.backend.repository;

import com.example.backend.model.TripSheet;
import com.example.backend.model.VehicleDeploymentPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripSheetRepository extends ApiRepository<TripSheet>, CustomApiRepository<TripSheet> {
    List<TripSheet> findByVehicleDeploymentPlanAndIsActiveTrue(VehicleDeploymentPlan vehicleDeploymentPlan);
}
