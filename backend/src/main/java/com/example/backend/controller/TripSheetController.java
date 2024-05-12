package com.example.backend.controller;

import com.example.backend.model.TripSheet;
import com.example.backend.repository.TripsheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip-sheets")
@RequiredArgsConstructor
public class TripSheetController {
    private final TripsheetRepository tripSheetRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/")
    public ResponseEntity<List<TripSheet>> getAllTripSheets() {
        List<TripSheet> tripSheets = tripSheetRepository.findAll();
        if (tripSheets.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(tripSheets);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<TripSheet> getTripSheetById(@PathVariable Long id) {
        Optional<TripSheet> tripSheets = tripSheetRepository.findById(id);
        return tripSheets.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/")
    public ResponseEntity<TripSheet> createTripSheet(@RequestBody TripSheet tripSheet) {
        TripSheet savedTripSheet = tripSheetRepository.save(tripSheet);
        //URI location = URI.create("/tripSheets/" + savedTripSheet.getId());
        //return ResponseEntity.created(location).body(savedTripSheet);
        return ResponseEntity.ok(savedTripSheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripSheet> updateTripSheet(@PathVariable Long id, @RequestBody TripSheet tripsheet) {
        if (tripSheetRepository.existsById(id)) {
            tripsheet.setId(id);
            TripSheet updatedTripsheet = tripSheetRepository.save(tripsheet);
            return ResponseEntity.ok(updatedTripsheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TripSheet> deleteTripsheet(@PathVariable Long id) {
        if (tripSheetRepository.existsById(id)) {
            Optional<TripSheet> tripSheet = tripSheetRepository.findById(id);
            tripSheetRepository.deleteById(id);
            return ResponseEntity.ok(tripSheet.get());
        }
        return ResponseEntity.notFound().build();
    }
}
