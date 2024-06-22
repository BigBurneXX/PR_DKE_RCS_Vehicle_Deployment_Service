package com.example.backend.service;

import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningOutputDTO;
import com.example.backend.model.Location;
import com.example.backend.model.Person;
import com.example.backend.model.Vehicle;
import com.example.backend.model.VehicleDeploymentPlanning;
import com.example.backend.repository.LocationRepository;
import com.example.backend.repository.PersonRepository;
import com.example.backend.repository.VehicleDeploymentPlanningRepository;
import com.example.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleRoutingService {
    private final SolverFactory<VehicleDeploymentPlanning> solverFactory;
    private final PersonRepository personRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;
    private final VehicleDeploymentPlanningRepository planningRepository;
    private final ModelMapper modelMapper;

    public VehicleDeploymentPlanningOutputDTO solve(VehicleDeploymentPlanningInputDTO inputPlanning) {
        // Create VehicleDeploymentPlanning
        VehicleDeploymentPlanning planning = new VehicleDeploymentPlanning();
        planningRepository.save(planning);

        // Save locations
        VehicleDeploymentPlanning finalPlanning = planning;
        Set<Location> locations = inputPlanning.addresses().stream()
                .map(addressDTO -> {
                    if(locationRepository.findByAddressId(addressDTO.id()).isEmpty()) {
                        Location location = new Location();
                        location.setAddressId(addressDTO.id());
                        location.setLatitude(addressDTO.latitude());
                        location.setLongitude(addressDTO.longitude());
                        location.setVehicleDeploymentPlanning(finalPlanning);
                        return locationRepository.save(location);
                    }
                    return null;
                }).collect(Collectors.toSet());

        // Save persons
        Set<Person> persons = inputPlanning.persons().stream()
                .map(inputPerson -> {
                    Person person = new Person();
                    person.setStartLocation(locationRepository.findByAddressId(inputPerson.startAddress()).orElseThrow());
                    person.setEndLocation(locationRepository.findByAddressId(inputPerson.targetAddress()).orElseThrow());
                    person.setHasWheelchair(inputPerson.hasWheelchair());
                    person.setVehicleDeploymentPlanning(finalPlanning);
                    return personRepository.save(person);
                }).collect(Collectors.toSet());

        // Save vehicles
        Set<Vehicle> vehicles = inputPlanning.vehicles().stream()
                .map(vehicleInputDTO -> {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setSeats(Math.toIntExact(vehicleInputDTO.seats()));
                    vehicle.setHasWheelchair("Y".equals(vehicleInputDTO.wheelchair()));
                    Location location = createLocation(vehicleInputDTO.startCoordinates());
                    locations.add(location);
                    vehicle.setStartLocation(location);
                    location = createLocation(vehicleInputDTO.endCoordinates());
                    locations.add(location);
                    vehicle.setEndLocation(location);
                    vehicle.setVehicleDeploymentPlanning(finalPlanning);
                    return vehicleRepository.save(vehicle);
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
}

