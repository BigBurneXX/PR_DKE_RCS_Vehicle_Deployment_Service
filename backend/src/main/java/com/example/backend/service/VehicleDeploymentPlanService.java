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

@Service
@RequiredArgsConstructor
public class VehicleDeploymentPlanService {
    private final VehicleDeploymentPlanRepository planRepository;
    private final VehicleDeploymentPlanningRepository planningRepository;
    private final ModelMapper modelMapper;

    public List<VehicleDeploymentPlanOutputDTO> getAllPlans() {
        return planRepository.findByIsActiveTrue().stream()
                .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<VehicleDeploymentPlanOutputDTO> getPlanById(Long id) {
        return planRepository.findByIdAndIsActiveTrue(id)
                .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class));
    }

    public Optional<VehicleDeploymentPlanOutputDTO> getPlanByVehicle(Long vehicleId) {
        return planRepository.findByVehicleIdAndIsActiveTrue(vehicleId)
                .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class));
    }

    public List<VehicleDeploymentPlanOutputDTO> getAllPlansByPlanning(Long id) {
        return planningRepository.findByIdAndIsActiveTrue(id)
                .map(planning -> planRepository.findByVehicleDeploymentPlanningAndIsActiveTrue(planning).stream()
                        .map(plan -> modelMapper.map(plan, VehicleDeploymentPlanOutputDTO.class))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public boolean existsPlanning(Long id) {
        return planningRepository.existsByIdAndIsActiveTrue(id);
    }
}
