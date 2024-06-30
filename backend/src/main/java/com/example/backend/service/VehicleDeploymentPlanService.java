package com.example.backend.service;

import com.example.backend.dto.VehicleDeploymentPlanOutputDTO;
import com.example.backend.repository.VehicleDeploymentPlanRepository;
import com.example.backend.repository.VehicleDeploymentPlanningRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing VehicleDeploymentPlans.
 */
@Service
@RequiredArgsConstructor
public class VehicleDeploymentPlanService {
    private final VehicleDeploymentPlanRepository planRepository;
    private final VehicleDeploymentPlanningRepository planningRepository;
    private final ModelMapper modelMapper;

    /**
     * Retrieves all active VehicleDeploymentPlans.
     *
     * @return a list of VehicleDeploymentPlanOutputDTOs
     */
    public List<VehicleDeploymentPlanOutputDTO> getAllPlans() {
        return planRepository.findByIsActiveTrue().stream()
                .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific active VehicleDeploymentPlan by its ID.
     *
     * @param id the ID of the plan
     * @return an optional containing the VehicleDeploymentPlanOutputDTO, if found
     */
    public Optional<VehicleDeploymentPlanOutputDTO> getPlanById(Long id) {
        return planRepository.findByIdAndIsActiveTrue(id)
                .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class));
    }

    /**
     * Retrieves active VehicleDeploymentPlans by vehicle ID.
     *
     * @param vehicleId the ID of the vehicle
     * @return a list of VehicleDeploymentPlanOutputDTOs
     */
    public List<VehicleDeploymentPlanOutputDTO> getPlansByVehicle(Long vehicleId) {
        return planRepository.findByVehicleIdAndIsActiveTrue(vehicleId).stream()
                .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all active VehicleDeploymentPlans by planning ID.
     *
     * @param id the ID of the planning
     * @return a list of VehicleDeploymentPlanOutputDTOs
     */
    public List<VehicleDeploymentPlanOutputDTO> getAllPlansByPlanning(Long id) {
        return planningRepository.findByIdAndIsActiveTrue(id)
                .map(planning -> planRepository.findByVehicleDeploymentPlanningAndIsActiveTrue(planning).stream()
                        .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    /**
     * Checks if a VehicleDeploymentPlanning exists by its ID.
     *
     * @param id the ID of the planning
     * @return true if the VehicleDeploymentPlanning exists, false otherwise
     */
    public boolean existsPlanning(Long id) {
        return planningRepository.existsByIdAndIsActiveTrue(id);
    }
}
