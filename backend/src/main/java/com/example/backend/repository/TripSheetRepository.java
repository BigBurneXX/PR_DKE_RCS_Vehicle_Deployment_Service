package com.example.backend.repository;

import com.example.backend.model.TripSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripSheetRepository extends JpaRepository<TripSheet, Long> {
}
