package com.example.backend.repository;

import com.example.backend.model.TripSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripsheetRepository extends JpaRepository<TripSheet, Long> {
}
