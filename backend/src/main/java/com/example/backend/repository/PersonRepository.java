package com.example.backend.repository;

import com.example.backend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByPersonId(Long id);
}
