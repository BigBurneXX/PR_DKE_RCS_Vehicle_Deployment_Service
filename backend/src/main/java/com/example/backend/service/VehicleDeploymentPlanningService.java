package com.example.backend.service;

import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningOutputDTO;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleDeploymentPlanningService {
    private final SolverFactory<com.example.backend.model.VehicleDeploymentPlanning> solverFactory;
    private final PersonRepository personRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;
    private final VehicleDeploymentPlanRepository planRepository;
    private final VehicleDeploymentPlanningRepository planningRepository;
    private final ModelMapper modelMapper;

    public VehicleDeploymentPlanningOutputDTO solve(VehicleDeploymentPlanningInputDTO inputPlanning) {
        // Create VehicleDeploymentPlanning
        VehicleDeploymentPlanning planning = new VehicleDeploymentPlanning();
        planningRepository.save(planning);

        // Save locations
        VehicleDeploymentPlanning finalPlanning = planning;
        Set<Location> locations = inputPlanning.addresses().stream()
                .map(addressDTO -> locationRepository.findByAddressId(addressDTO.id())
                        .orElseGet(() -> {
                            Location location = new Location();
                            location.setAddressId(addressDTO.id());
                            location.setLatitude(addressDTO.latitude());
                            location.setLongitude(addressDTO.longitude());
                            location.setVehicleDeploymentPlanning(finalPlanning);
                            return locationRepository.save(location);
                        }))
                .collect(Collectors.toSet());

        // Save persons
        Set<Person> persons = inputPlanning.persons().stream()
                .map(inputPerson -> {
                    Person p = new Person();
                    p.setPersonId(inputPerson.id());
                    p.setStartLocation(locationRepository.findByAddressId(inputPerson.startAddress()).orElseThrow());
                    p.setEndLocation(locationRepository.findByAddressId(inputPerson.targetAddress()).orElseThrow());
                    p.setHasWheelchair(inputPerson.hasWheelchair());
                    p.setVehicleDeploymentPlanning(finalPlanning);
                    return personRepository.save(p);
                }).collect(Collectors.toSet());

        // Save vehicles
        Set<Vehicle> vehicles = inputPlanning.vehicles().stream()
                .map(vehicleInputDTO -> {
                    Vehicle v = new Vehicle();
                    v.setVehicleId(vehicleInputDTO.id());
                    v.setSeats(Math.toIntExact(vehicleInputDTO.seats()));
                    v.setHasWheelchair("Y".equals(vehicleInputDTO.wheelchair()));
                    Location startLocation = createLocation(vehicleInputDTO.startCoordinates());
                    locations.add(startLocation);
                    v.setStartLocation(startLocation);
                    Location endLocation = createLocation(vehicleInputDTO.endCoordinates());
                    locations.add(endLocation);
                    v.setEndLocation(endLocation);
                    v.setVehicleDeploymentPlanning(finalPlanning);
                    return vehicleRepository.save(v);
                }).collect(Collectors.toSet());

        // Create and save VehicleDeploymentPlanning
        planning.setPersons(persons);
        planning.setVehicles(vehicles);
        planning.setLocations(locations);
        planning = planningRepository.save(planning);

        // Solve the problem
        Solver<VehicleDeploymentPlanning> solver = solverFactory.buildSolver();
        VehicleDeploymentPlanning solution = solver.solve(planning);

        // Save the solution
        planRepository.saveAll(solution.getPlans());
        planningRepository.save(solution);

        return modelMapper.map(solution, VehicleDeploymentPlanningOutputDTO.class);
    }

    private Location createLocation(String coordinates) {
        String[] coordinate = coordinates.split(",");
        Location location = new Location();
        location.setLatitude(coordinate[0]);
        location.setLongitude(coordinate[1]);
        return locationRepository.save(location);
    }

    public List<VehicleDeploymentPlanningOutputDTO> getAllPlannings() {
        return planningRepository.findByIsActiveTrue().stream()
                .map(planning -> modelMapper.map(planning, VehicleDeploymentPlanningOutputDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<VehicleDeploymentPlanningOutputDTO> getPlanningById(Long id) {
        return planningRepository.findByIdAndIsActiveTrue(id)
                .map(planning -> modelMapper.map(planning, VehicleDeploymentPlanningOutputDTO.class));
    }

    public void deletePlanning(Long id) {
        planningRepository.softDelete(id, VehicleDeploymentPlanning.class);
    }

    public boolean existsPlanning(Long id){
        return planningRepository.existsByIdAndIsActiveTrue(id);
    }
}

