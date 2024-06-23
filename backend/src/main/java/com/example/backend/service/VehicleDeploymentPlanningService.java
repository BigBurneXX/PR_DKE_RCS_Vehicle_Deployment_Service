package com.example.backend.service;

import com.example.backend.dto.AddressInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningOutputDTO;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleDeploymentPlanningService {
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
        for(AddressInputDTO address : inputPlanning.addresses()) {
            Optional<Location> possLocation = locationRepository.findByAddressId(address.id());
            Location l = possLocation.orElseGet(Location::new);
            l.setAddressId(address.id());
            l.setLatitude(Double.parseDouble(address.latitude()));
            l.setLongitude(Double.parseDouble(address.longitude()));
            locationRepository.save(l);
        }

        // Save persons
        Set<Person> persons = inputPlanning.persons().stream()
                .map(inputPerson -> {
                    Optional<Person> possPerson = personRepository.findByPersonId(inputPerson.id());
                    Person p = possPerson.orElseGet(Person::new);
                    p.setPersonId(inputPerson.id());
                    p.setStartLocation(locationRepository.findByAddressId(inputPerson.startAddress()).orElseThrow());
                    p.setEndLocation(locationRepository.findByAddressId(inputPerson.targetAddress()).orElseThrow());
                    p.setHasWheelchair(inputPerson.hasWheelchair());
                    p.setVehicleDeploymentPlanning(finalPlanning);
                    return personRepository.save(p);
                }).collect(Collectors.toSet());

        // Save vehicles
        Set<Vehicle> vehicles = inputPlanning.vehicles().stream()
                .map(inputVehicle -> {
                    Optional<Vehicle> possVehicle = vehicleRepository.findByVehicleId(inputVehicle.id());
                    Vehicle v = possVehicle.orElseGet(Vehicle::new);
                    v.setVehicleId(inputVehicle.id());
                    v.setSeats(Math.toIntExact(inputVehicle.seats()));
                    v.setCanCarryWheelchair("Y".equals(inputVehicle.wheelchair()));
                    v.setStartLocation(createLocation(inputVehicle.startCoordinates()));
                    v.setEndLocation(createLocation(inputVehicle.endCoordinates()));
                    v.setVehicleDeploymentPlanning(finalPlanning);
                    return vehicleRepository.save(v);
                }).collect(Collectors.toSet());

        // Create and save VehicleDeploymentPlanning
        planning.setPersons(persons);
        planning.setVehicles(vehicles);
        planning = planningRepository.save(planning);

        // Initiate solverFactory
        // The solver runs only for 10 seconds on this small dataset.
        // It's recommended to run for at least 5 minutes ("5m") otherwise.
        SolverFactory<VehicleDeploymentPlanning> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(VehicleDeploymentPlanning.class)
                .withEntityClasses(Person.class)
                .withConstraintProviderClass(VehicleRoutingConstraintProvider.class)
                // The solver runs only for 10 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(10)));

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
        location.setLatitude(Double.parseDouble(coordinate[0]));
        location.setLongitude(Double.parseDouble(coordinate[1]));
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

