package com.example.backend.repository;

import com.example.backend.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for entities with metadata.
 * Extends JpaRepository to provide standard CRUD operations.
 *
 * @param <T> the type of the entity extending MetaData
 */
@NoRepositoryBean
public interface ApiRepository<T extends MetaData> extends JpaRepository<T, Long> {
    /**
     * Finds all active entities.
     *
     * @return a list of active entities
     */
    List<T> findByIsActiveTrue();

    /**
     * Finds an active entity by its ID.
     *
     * @param id the ID of the entity
     * @return an Optional containing the found entity, or empty if not found
     */
    Optional<T> findByIdAndIsActiveTrue(Long id);

    /**
     * Checks if an active entity exists by its ID.
     *
     * @param id the ID of the entity
     * @return true if the entity exists and is active, false otherwise
     */
    boolean existsByIdAndIsActiveTrue(Long id);
}
