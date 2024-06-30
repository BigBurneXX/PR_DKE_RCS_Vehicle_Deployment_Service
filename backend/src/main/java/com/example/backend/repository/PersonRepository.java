package com.example.backend.repository;

import com.example.backend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Person entities.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * Finds a Person entity by its person ID.
     *
     * @param id the person ID
     * @return an Optional containing the found Person, or empty if not found
     */
    Optional<Person> findByPersonId(Long id);
}
