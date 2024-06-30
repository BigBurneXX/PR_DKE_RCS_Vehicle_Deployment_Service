package com.example.backend.repository;

import com.example.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Location entities.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    /**
     * Finds a Location entity by its address ID.
     *
     * @param addressId the address ID
     * @return an Optional containing the found Location, or empty if not found
     */
    Optional<Location> findByAddressId(Long addressId);
}
