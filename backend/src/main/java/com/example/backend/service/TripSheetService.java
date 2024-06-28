package com.example.backend.service;

import com.example.backend.dto.PersonOutputDTO;
import com.example.backend.dto.TripSheetOutputDTO;
import com.example.backend.model.Person;
import com.example.backend.model.TripSheet;
import com.example.backend.repository.TripSheetRepository;
import com.example.backend.repository.VehicleDeploymentPlanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public TripSheetOutputDTO createTripSheet(Long vehicleDeploymentPlanId, Set<PersonOutputDTO> persons) {
        return vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentPlanId)
                .map(plan -> {
                    TripSheet tripSheet = tripSheetRepository.save(new TripSheet());
                    tripSheet.setName(vehicleDeploymentPlanRepository.findById(vehicleDeploymentPlanId).get().getName() +
                            "_" + tripSheet.getId());
                    tripSheet.setVehicleDeploymentPlan(plan);
                    tripSheet.setPersons(persons.stream()
                            .map(person -> modelMapper.map(person, Person.class))
                            .collect(Collectors.toSet()));
                    tripSheetRepository.save(tripSheet);
                    return modelMapper.map(tripSheet, TripSheetOutputDTO.class);
                }).orElseThrow();
    }

    public List<TripSheetOutputDTO> getByPlanId(Long vehicleDeploymentId) {
        return vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentId)
                .map(plan -> tripSheetRepository.findByVehicleDeploymentPlanAndIsActiveTrue(plan).stream()
                        .map(tripSheet -> modelMapper.map(tripSheet, TripSheetOutputDTO.class))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public List<TripSheetOutputDTO> getAllTripSheets() {
        return tripSheetRepository.findByIsActiveTrue().stream()
                .map(tripSheet -> modelMapper.map(tripSheet, TripSheetOutputDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<TripSheetOutputDTO> getTripSheetById(Long id) {
        return tripSheetRepository.findByIdAndIsActiveTrue(id)
                .map(tripSheet -> modelMapper.map(tripSheet, TripSheetOutputDTO.class));
    }

    public void deleteTripSheet(Long id) {
        tripSheetRepository.softDelete(id, TripSheet.class);
    }

    public boolean existsTripSheet(Long id) {
        return tripSheetRepository.existsByIdAndIsActiveTrue(id);
    }

    public boolean existsPlan(Long id) {
        return vehicleDeploymentPlanRepository.existsByIdAndIsActiveTrue(id);
    }
}
