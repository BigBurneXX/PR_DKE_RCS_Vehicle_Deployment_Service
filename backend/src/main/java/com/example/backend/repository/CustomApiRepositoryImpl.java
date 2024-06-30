package com.example.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the CustomApiRepository interface.
 *
 * @param <T> the type of the entity
 */
@Repository
public class CustomApiRepositoryImpl<T> implements CustomApiRepository<T> {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Soft deletes an entity by setting its isActive field to false.
     *
     * @param entityId the ID of the entity to be soft deleted
     * @param entityClass the class of the entity
     */
    @Override
    @Transactional
    public void softDelete(Long entityId, Class<T> entityClass) {
        T entity = entityManager.find(entityClass, entityId);
        if (entity != null) {
            try {
                entity.getClass().getMethod("setIsActive", boolean.class).invoke(entity, false);
                entityManager.merge(entity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
