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

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing TripSheets.
 */
@Service
@RequiredArgsConstructor
public class TripSheetService {
    private final TripSheetRepository tripSheetRepository;
    private final VehicleDeploymentPlanRepository vehicleDeploymentPlanRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new TripSheet for the specified VehicleDeploymentPlan.
     *
     * @param vehicleDeploymentPlanId the ID of the VehicleDeploymentPlan
     * @param persons a set of PersonOutputDTOs
     * @return the created TripSheetOutputDTO
     */
    public TripSheetOutputDTO createTripSheet(Long vehicleDeploymentPlanId, Set<PersonOutputDTO> persons) {
        return vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentPlanId)
                .map(plan -> {
                    TripSheet tripSheet = tripSheetRepository.save(new TripSheet());
                    tripSheet.setName(vehicleDeploymentPlanRepository.findById(vehicleDeploymentPlanId).get().getName() +
                            "_tripSheet_" + tripSheet.getId());
                    tripSheet.setVehicleDeploymentPlan(plan);
                    tripSheet.setVehicle(plan.getVehicle());
                    tripSheet.setPersons(persons.stream()
                            .map(person -> modelMapper.map(person, Person.class))
                            .collect(Collectors.toSet()));
                    tripSheet.getOptimizedRoute();
                    tripSheetRepository.save(tripSheet);
                    plan.addTripSheet(tripSheet);
                    vehicleDeploymentPlanRepository.save(plan);
                    return modelMapper.map(tripSheet, TripSheetOutputDTO.class);
                }).orElseThrow();
    }

    /**
     * Retrieves all TripSheets associated with a specific VehicleDeploymentPlan ID.
     *
     * @param vehicleDeploymentId the ID of the VehicleDeploymentPlan
     * @return a list of TripSheetOutputDTOs
     */
    public List<TripSheetOutputDTO> getByPlanId(Long vehicleDeploymentId) {
        return vehicleDeploymentPlanRepository.findByIdAndIsActiveTrue(vehicleDeploymentId)
                .map(plan -> tripSheetRepository.findByVehicleDeploymentPlanAndIsActiveTrue(plan).stream()
                        .map(tripSheet -> modelMapper.map(tripSheet, TripSheetOutputDTO.class))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    /**
     * Retrieves all active TripSheets.
     *
     * @return a list of TripSheetOutputDTOs
     */
    public List<TripSheetOutputDTO> getAllTripSheets() {
        return tripSheetRepository.findByIsActiveTrue().stream()
                .map(tripSheet -> modelMapper.map(tripSheet, TripSheetOutputDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an active TripSheet by its ID.
     *
     * @param id the ID of the TripSheet
     * @return an optional containing the TripSheetOutputDTO, if found
     */
    public Optional<TripSheetOutputDTO> getTripSheetById(Long id) {
        return tripSheetRepository.findByIdAndIsActiveTrue(id)
                .map(tripSheet -> modelMapper.map(tripSheet, TripSheetOutputDTO.class));
    }

    /**
     * Soft deletes a TripSheet by its ID.
     *
     * @param id the ID of the TripSheet to delete
     */
    public void deleteTripSheet(Long id) {
        tripSheetRepository.softDelete(id, TripSheet.class);
    }

    /**
     * Checks if an active TripSheet exists by its ID.
     *
     * @param id the ID of the TripSheet
     * @return true if the TripSheet exists, false otherwise
     */
    public boolean existsTripSheet(Long id) {
        return tripSheetRepository.existsByIdAndIsActiveTrue(id);
    }

    /**
     * Checks if an active VehicleDeploymentPlan exists by its ID.
     *
     * @param id the ID of the VehicleDeploymentPlan
     * @return true if the VehicleDeploymentPlan exists, false otherwise
     */
    public boolean existsPlan(Long id) {
        return vehicleDeploymentPlanRepository.existsByIdAndIsActiveTrue(id);
    }
}
