package com.example.backend.service;

import com.example.backend.dto.LocationDTO;
import com.example.backend.dto.TripSheetOutputDTO;
import com.example.backend.model.Location;
import com.example.backend.model.TripSheet;
import com.example.backend.model.VehicleDeploymentPlan;
import com.example.backend.repository.TripSheetRepository;
import com.example.backend.repository.VehicleDeploymentPlanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripSheetService {
    private final TripSheetRepository tripSheetRepository;
    private final VehicleDeploymentPlanRepository vehicleDeploymentPlanRepository;
    private final ModelMapper modelMapper;

    public TripSheetOutputDTO createTripSheet(Long vehicleDeploymentPlanId, Set<LocationDTO> locations) {
        Optional<VehicleDeploymentPlan> plan = vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentPlanId);
        if(plan.isPresent()) {
            TripSheet t = new TripSheet();
            t.setVehicleDeploymentPlan(plan.get());
            t.setLocations(locations.stream()
                    .map(locationDTO -> modelMapper.map(locationDTO, Location.class))
                    .collect(Collectors.toSet()));
            tripSheetRepository.save(t);
            return modelMapper.map(t, TripSheetOutputDTO.class);
        }
        return null;
    }

    public List<TripSheetOutputDTO> getByPlanId(Long vehicleDeploymentId) {
        Optional<VehicleDeploymentPlan> plan = vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentId);
        if(plan.isPresent()) {
            List<TripSheet> tripSheets = tripSheetRepository.findByVehicleDeploymentPlanAndIsActiveTrue(plan.get());
            return tripSheets.stream()
                    .map(tripSheet -> modelMapper.map(tripSheet, TripSheetOutputDTO.class))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
