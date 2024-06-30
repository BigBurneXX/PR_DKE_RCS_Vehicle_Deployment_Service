package com.example.backend.repository;

import org.springframework.stereotype.Repository;

/**
 * Custom repository interface for performing additional operations.
 *
 * @param <T> the type of the entity
 */
@Repository
public interface CustomApiRepository<T> {
    /**
     * Soft deletes an entity by setting its isActive field to false.
     *
     * @param entityId the ID of the entity to be soft deleted
     * @param entityClass the class of the entity
     */
    void softDelete(Long entityId, Class<T> entityClass);
}
