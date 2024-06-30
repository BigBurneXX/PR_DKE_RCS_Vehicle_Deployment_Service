package com.example.backend.repository;

import com.example.backend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Vehicle entities.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    /**
     * Finds a Vehicle entity by its vehicle ID.
     *
     * @param id the vehicle ID
     * @return an Optional containing the found Vehicle, or empty if not found
     */
    Optional<Vehicle> findByVehicleId(Long id);
}
