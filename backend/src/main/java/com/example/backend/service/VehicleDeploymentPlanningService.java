package com.example.backend.service;

import com.example.backend.dto.*;
import com.example.backend.model.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing VehicleDeploymentPlanning.
 */
@Service
@RequiredArgsConstructor
public class VehicleDeploymentPlanningService {
    private final PersonRepository personRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;
    private final VehicleDeploymentPlanRepository planRepository;
    private final VehicleDeploymentPlanningRepository planningRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new VehicleDeploymentPlanning.
     *
     * @param inputPlanning the VehicleDeploymentPlanningInputDTO containing the planning details.
     * @return the created VehicleDeploymentPlanningOutputDTO
     */
    public VehicleDeploymentPlanningOutputDTO createPlanning(VehicleDeploymentPlanningInputDTO inputPlanning) {
        // Create VehicleDeploymentPlanning
        VehicleDeploymentPlanning planning = planningRepository.save(new VehicleDeploymentPlanning());

        // Set the name
        planning.setName(inputPlanning.name() == null ? "Planning_" + planning.getId() : inputPlanning.name());

        // Save locations, persons and vehicles
        saveLocations(inputPlanning.addresses());
        Set<Person> persons = savePersons(inputPlanning.persons());
        Set<Vehicle> vehicles = saveVehicles(inputPlanning.vehicles());

        // Add vehicles and person to planning
        planning.setVehicles(vehicles);
        planning.setPersons(persons);

        // It's essential to set the vehicles and persons before solving
        Set<VehicleDeploymentPlan> plans = solve(planning);
        planning.setPlans(plans);

        printPlansToConsole(planning.getPlans());

        // Save the solved planning
        planningRepository.save(planning);

        return modelMapper.map(planning, VehicleDeploymentPlanningOutputDTO.class);
    }

    /**
     * Saves the locations from the VehicleDeploymentPlanningInputDTO.
     *
     * @param inputAddresses the addresses of the VehicleDeploymentPlanningInputDTO.
     */
    private void saveLocations(Set<AddressInputDTO> inputAddresses) {
        for(AddressInputDTO address : inputAddresses) {
            Optional<Location> possLocation = locationRepository.findByAddressId(address.id());
            Location l = possLocation.orElseGet(Location::new);
            l.setAddressId(address.id());
            l.setLatitude(Double.parseDouble(address.latitude()));
            l.setLongitude(Double.parseDouble(address.longitude()));
            locationRepository.save(l);
        }
    }

    /**
     * Saves the persons from the VehicleDeploymentPlanningInputDTO.
     *
     * @param inputPersons the persons of the VehicleDeploymentPlanningInputDTO.
     * @return a set of saved persons
     */
    private Set<Person> savePersons(Set<PersonInputDTO> inputPersons) {
        return inputPersons.stream()
                .map(inputPerson -> {
                    Optional<Person> possPerson = personRepository.findByPersonId(inputPerson.id());
                    Person p = possPerson.orElseGet(Person::new);
                    p.setPersonId(inputPerson.id());
                    p.setName(inputPerson.first_name() + " " + inputPerson.last_name());
                    try {
                        p.setDateOfBirth(DateFormat.getInstance().parse(inputPerson.date_of_birth()));
                    } catch (ParseException e) {
                        System.out.println("An error occurred when attempting to set date of birth for " + inputPerson.id());
                    }
                    p.setStartLocation(locationRepository.findByAddressId(inputPerson.start_address()).orElseThrow());
                    p.setEndLocation(locationRepository.findByAddressId(inputPerson.target_address()).orElseThrow());
                    p.setHasWheelchair(inputPerson.has_wheelchair());
                    return personRepository.save(p);
                }).collect(Collectors.toSet());
    }

    /**
     * Saves the vehicles from the VehicleDeploymentPlanningInputDTO.
     *
     * @param inputVehicles the vehicles of the VehicleDeploymentPlanningInputDTO.
     * @return a set of saved vehicles
     */
    private Set<Vehicle> saveVehicles(Set<VehicleInputDTO> inputVehicles) {
        return inputVehicles.stream()
                .map(inputVehicle -> {
                    Optional<Vehicle> possVehicle = vehicleRepository.findByVehicleId(inputVehicle.id());
                    Vehicle v = possVehicle.orElseGet(Vehicle::new);
                    v.setVehicleId(inputVehicle.id());
                    v.setName(inputVehicle.vehicle_name());
                    v.setType(inputVehicle.vehicle_type());
                    if(v.getVehicleId() == 3952) {
                        v.setSeats(2);
                        v.setCanCarryWheelchair(true);
                    } else {
                        v.setSeats(Math.toIntExact(inputVehicle.seats()));
                        v.setCanCarryWheelchair("Y".equals(inputVehicle.wheelchair()));
                    }
                    v.setStartLocation(createLocation(inputVehicle.start_coordinates()));
                    v.setEndLocation(createLocation(inputVehicle.end_coordinates()));
                    return vehicleRepository.save(v);
                }).collect(Collectors.toSet());
    }

    /**
     * Creates a location entity from the provided coordinates string.
     *
     * @param coordinates the coordinates string
     * @return the created location
     */
    private Location createLocation(String coordinates) {
        String[] coordinate = coordinates.split(",");
        Location location = new Location();
        location.setLatitude(Double.parseDouble(coordinate[1]));
        location.setLongitude(Double.parseDouble(coordinate[0]));
        return locationRepository.save(location);
    }

    /**
     * Solves the vehicle deployment problem.
     *
     * @param planning the VehicleDeploymentPlanning entity
     * @return a set of solved VehicleDeploymentPlans
     */
    private Set<VehicleDeploymentPlan> solve(VehicleDeploymentPlanning planning) {
        // Initiate solverFactory
        SolverFactory<VehicleDeploymentPlanning> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(VehicleDeploymentPlanning.class)
                .withEntityClasses(Person.class)
                .withConstraintProviderClass(VehicleRoutingConstraintProvider.class)
                // The solver runs only for 5 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

        // Solve the problem
        Solver<VehicleDeploymentPlanning> solver = solverFactory.buildSolver();
        VehicleDeploymentPlanning solution = solver.solve(planning);

        // Represents a map where every vehicle is mapped to the persons it should transport
        Map<Vehicle, List<Person>> vehiclePersonMap = solution.getPersons().stream()
                .collect(Collectors.groupingBy(Person::getAssignedVehicle));
        return savePlans(vehiclePersonMap, planning);
    }

    /**
     * Saves the VehicleDeploymentPlans based on the provided vehicle and person mapping.
     *
     * @param vehiclePersonMap a map of vehicles to their assigned persons
     * @param planning the VehicleDeploymentPlanning entity
     * @return a set of saved VehicleDeploymentPlans
     */
    private Set<VehicleDeploymentPlan> savePlans(Map<Vehicle, List<Person>> vehiclePersonMap,
                                                       VehicleDeploymentPlanning planning) {
        Set<VehicleDeploymentPlan> vehiclePlans = new HashSet<>();
        for (Map.Entry<Vehicle, List<Person>> entry : vehiclePersonMap.entrySet()) {
            Vehicle vehicle = entry.getKey();
            Set<Person> assignedPersons = new HashSet<>(entry.getValue());
            VehicleDeploymentPlan plan =  planRepository.save(new VehicleDeploymentPlan());
            plan.setName(planning.getName() + "_plan_" + plan.getId());
            plan.setVehicle(vehicle);
            plan.setPersons(assignedPersons);
            plan.setVehicleDeploymentPlanning(planning);
            plan.getOptimizedRoute();
            planRepository.save(plan);
            vehiclePlans.add(plan);
        }
        return vehiclePlans;
    }

    /**
     * Prints the VehicleDeploymentPlans to the console for debugging purposes.
     *
     * @param plans the set of VehicleDeploymentPlans
     */
    private void printPlansToConsole(Set<VehicleDeploymentPlan> plans) {
        for (VehicleDeploymentPlan vehiclePlan : plans) {
            System.out.println("Vehicle: " + vehiclePlan.getVehicle().getId());
            System.out.println("Persons: " + vehiclePlan.getPersons().stream().map(person -> String.valueOf(person.getId())).
                    collect(Collectors.joining(", ")));
            //System.out.println("Total Distance: " + vehiclePlan.getTotalDistance());
            System.out.println("Optimized Route: " + vehiclePlan.getLocations().stream()
                    .map(location -> "(" + location.getLatitude() + ", " + location.getLongitude() + ")")
                    .collect(Collectors.joining(" -> ")));
            System.out.println();
        }
    }

    /**
     * Retrieves all active VehicleDeploymentPlannings.
     *
     * @return a list of VehicleDeploymentPlanningOutputDTOs
     */
    public List<VehicleDeploymentPlanningOutputDTO> getAllPlannings() {
        return planningRepository.findByIsActiveTrue().stream()
                .map(planning -> modelMapper.map(planning, VehicleDeploymentPlanningOutputDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an active VehicleDeploymentPlanning by its ID.
     *
     * @param id the ID of the VehicleDeploymentPlanning
     * @return an optional containing the vVehicleDeploymentPlanningOutputDTO, if found
     */
    public Optional<VehicleDeploymentPlanningOutputDTO> getPlanningById(Long id) {
        return planningRepository.findByIdAndIsActiveTrue(id)
                .map(planning -> modelMapper.map(planning, VehicleDeploymentPlanningOutputDTO.class));
    }

    /**
     * Soft deletes a VehicleDeploymentPlanning by its ID and all corresponding VehicleDeploymentPlans
     *
     * @param id the ID of the VehicleDeploymentPlanning to delete
     */
    public void deletePlanning(Long id) {
        Optional<VehicleDeploymentPlanning> planning = planningRepository.findByIdAndIsActiveTrue(id);
        // delete all corresponding plans
        if(planning.isPresent())
            for(VehicleDeploymentPlan plan : planning.get().getPlans())
                planRepository.softDelete(plan.getId(), VehicleDeploymentPlan.class);
        // delete the planning itself
        planningRepository.softDelete(id, VehicleDeploymentPlanning.class);
    }

    /**
     * Checks if an active VehicleDeploymentPlanning exists by its ID.
     *
     * @param id the ID of the VehicleDeploymentPlanning
     * @return true if the VehicleDeploymentPlanning exists, false otherwise
     */
    public boolean existsPlanning(Long id){
        return planningRepository.existsByIdAndIsActiveTrue(id);
    }
}

