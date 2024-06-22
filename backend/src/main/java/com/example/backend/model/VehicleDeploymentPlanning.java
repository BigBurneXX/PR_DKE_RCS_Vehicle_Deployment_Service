package com.example.backend.model;

import com.example.backend.dto.AddressDTO;
import com.example.backend.dto.PersonInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import com.example.backend.dto.VehicleInputDTO;
import jakarta.persistence.*;
import lombok.*;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@PlanningSolution
@Entity
public class VehicleDeploymentPlanning extends MetaData {
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "personRange")
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Person> persons;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<Location> locations = new HashSet<>();

    @PlanningEntityCollectionProperty
    @OneToMany(mappedBy = "vehicleDeploymentPlanning")
    private Set<VehicleDeploymentPlan> plans = new HashSet<>();

    @PlanningScore
    private HardSoftScore score;

    public VehicleDeploymentPlanning(VehicleDeploymentPlanningInputDTO vehicleDeploymentPlanningInputDTO) {
        this.persons = convertToPersons(vehicleDeploymentPlanningInputDTO.persons(), vehicleDeploymentPlanningInputDTO.addresses());
        this.vehicles = convertToVehicles(vehicleDeploymentPlanningInputDTO.vehicles());
        this.locations = Stream.concat(
                persons.stream().flatMap(p -> Stream.of(p.getStartLocation(), p.getEndLocation())),
                vehicles.stream().flatMap(v -> Stream.of(v.getStartLocation(), v.getEndLocation()))
        ).collect(Collectors.toSet());
    }

    private Set<Person> convertToPersons(Set<PersonInputDTO> inputPersons, Set<AddressDTO> inputAddresses) {
        Map<Long, AddressDTO> addressMap = inputAddresses.stream().collect(Collectors.toMap(AddressDTO::id, address -> address));
        return inputPersons.stream().map(inputPerson -> {
            Person p = new Person();
            p.setStartLocation(createLocation(inputPerson.startAddress(), addressMap));
            p.setEndLocation(createLocation(inputPerson.targetAddress(), addressMap));
            p.setHasWheelchair(inputPerson.hasWheelchair());
            return p;
        }).collect(Collectors.toSet());
    }

    private Location createLocation(Long addressId, Map<Long, AddressDTO> addressMap) {
        AddressDTO address = addressMap.get(addressId);
        if (address != null) {
            Location location = new Location();
            location.setAddressId(addressId);
            location.setLatitude(address.latitude());
            location.setLongitude(address.longitude());
            return location;
        }
        return null;
    }

    private Set<Vehicle> convertToVehicles(Set<VehicleInputDTO> inputVehicles) {
        return inputVehicles.stream().map(inputVehicle -> {
            Vehicle v = new Vehicle();
            v.setStartLocation(createLocation(inputVehicle.startCoordinates()));
            v.setEndLocation(createLocation(inputVehicle.endCoordinates()));
            v.setSeats(Math.toIntExact(inputVehicle.seats()));
            v.setHasWheelchair("Y".equals(inputVehicle.wheelchair()));
            return v;
        }).collect(Collectors.toSet());

    }

    private Location createLocation(String coordinates) {
        String[] coordinate = coordinates.split(" ");
        Location location = new Location();
        location.setLatitude(coordinate[0]);
        location.setLongitude(coordinate[1]);
        return location;
    }
}
