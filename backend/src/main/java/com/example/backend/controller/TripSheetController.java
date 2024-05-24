package com.example.backend.controller;

import com.example.backend.model.TripSheet;
import com.example.backend.repository.TripSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip-sheets")
@RequiredArgsConstructor
public class TripSheetController {
    private final TripSheetRepository tripSheetRepository;

    @GetMapping("/")
    public ResponseEntity<List<TripSheet>> getAllTripSheets() {
        List<TripSheet> tripSheets = tripSheetRepository.findAll();
        if (tripSheets.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(tripSheets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripSheet> getTripSheetById(@PathVariable Long id) {
        Optional<TripSheet> tripSheets = tripSheetRepository.findById(id);
        return tripSheets.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<TripSheet> createTripSheet(@RequestBody TripSheet tripSheet) {
        TripSheet savedTripSheet = tripSheetRepository.save(tripSheet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTripSheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripSheet> updateTripSheet(@PathVariable Long id, @RequestBody TripSheet tripsheet) {
        if (tripSheetRepository.existsById(id)) {
            tripsheet.setId(id);
            TripSheet updatedTripsheet = tripSheetRepository.save(tripsheet);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTripsheet);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TripSheet> deleteTripSheet(@PathVariable Long id) {
        if (tripSheetRepository.existsById(id)) {
            Optional<TripSheet> tripSheet = tripSheetRepository.findById(id);
            tripSheetRepository.deleteById(id);
            return ResponseEntity.ok(tripSheet.get());
        }
        return ResponseEntity.notFound().build();
    }
}
