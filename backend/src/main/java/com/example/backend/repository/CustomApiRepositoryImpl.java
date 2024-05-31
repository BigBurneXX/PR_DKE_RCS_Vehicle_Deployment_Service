package com.example.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CustomApiRepositoryImpl<T> implements CustomApiRepository<T> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public T softDelete(Long entityId, Class<T> entityClass) {
        T entity = entityManager.find(entityClass, entityId);
        if (entity != null) {
            try {
                entity.getClass().getMethod("setIsActive", boolean.class).invoke(entity, false);
                entityManager.merge(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
}
