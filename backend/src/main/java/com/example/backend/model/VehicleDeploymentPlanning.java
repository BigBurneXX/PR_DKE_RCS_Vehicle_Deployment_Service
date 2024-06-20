package com.example.backend.model;

import com.example.backend.dto.AddressDTO;
import com.example.backend.dto.PersonInputDTO;
import com.example.backend.dto.VehicleDeploymentPlanningInputDTO;
import jakarta.persistence.*;
import lombok.*;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.HashSet;
import java.util.Set;

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
        //this.persons = vehicleDeploymentPlanningInputDTO.persons();
        this.vehicles = vehicleDeploymentPlanningInputDTO.vehicles();
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.persons().stream().map(Person::getStartLocation).toList());
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.persons().stream().map(Person::getEndLocation).toList());
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.vehicles().stream().map(Vehicle::getStartLocation).toList());
        this.locations.addAll(vehicleDeploymentPlanningInputDTO.vehicles().stream().map(Vehicle::getEndLocation).toList());
    }

    private Set<Person> convertToPersons(Set<PersonInputDTO> inputPersons, Set<AddressDTO> inputAddresses) {
        Set<Person> retPersons = new HashSet<>();
        for(PersonInputDTO inputPerson : inputPersons) {
            Person p = new Person();
            Location location1 = new Location();
            Long addressId = inputPerson.startAddress();
            AddressDTO address = findAddress(addressId, inputAddresses);
            if(address != null) {
                location1.setAddressId(addressId);
                location1.setLatitude(address.latitude());
                location1.setLongitude(address.longitude());
            }
            Location location2 = new Location();
            addressId = inputPerson.targetAddress();
            address = findAddress(addressId, inputAddresses);
            if(address != null) {
                location2.setAddressId(addressId);
                location2.setLatitude(address.latitude());
                location2.setLongitude(address.longitude());
            }
            p.setStartLocation(location1);
            p.setEndLocation(location2);
            p.setHasWheelchair(inputPerson.hasWheelchair());
            retPersons.add(p);
        }
        return retPersons;
    }

    private AddressDTO findAddress(Long id, Set<AddressDTO> inputAddresses) {
        for(AddressDTO address : inputAddresses) {
            if(id == address.id())
                return address;
        }
        return null;
    }
}
