package com.example.backend.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomApiRepository<T> {
    T softDelete(Long entityId, Class<T> entityClass);
}
