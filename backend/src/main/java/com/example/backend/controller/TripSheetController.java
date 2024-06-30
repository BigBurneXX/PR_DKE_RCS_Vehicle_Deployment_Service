package com.example.backend.controller;

import com.example.backend.dto.PersonOutputDTO;
import com.example.backend.dto.TripSheetOutputDTO;
import com.example.backend.service.TripSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Controller for handling trip sheet-related requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/trip-sheets")
public class TripSheetController {
    private final TripSheetService tripSheetService;

    /**
     * Retrieves all trip sheets.
     *
     * @return a ResponseEntity containing a list of TripSheetOutputDTOs.
     */
    @GetMapping
    public ResponseEntity<List<TripSheetOutputDTO>> getAllTripSheets() {
        List<TripSheetOutputDTO> tripSheets = tripSheetService.getAllTripSheets();
        return tripSheets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tripSheets);
    }

    /**
     * Retrieves a trip sheet by its ID.
     *
     * @param id the ID of the trip sheet.
     * @return a ResponseEntity containing the TripSheetOutputDTO or a not found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TripSheetOutputDTO> getTripSheetById(@PathVariable Long id) {
        return tripSheetService.getTripSheetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves trip sheets by vehicle deployment plan ID.
     *
     * @param vehicleDeploymentPlanId the ID of the vehicle deployment plan.
     * @return a ResponseEntity containing a list of TripSheetOutputDTOs.
     */
    @GetMapping("/vehicleDeploymentPlan/{vehicleDeploymentPlanId}")
    public ResponseEntity<List<TripSheetOutputDTO>> getTripSheetsByVehicleDeploymentPlan(@PathVariable Long vehicleDeploymentPlanId) {
        List<TripSheetOutputDTO> tripSheets = tripSheetService.getByPlanId(vehicleDeploymentPlanId);
        return tripSheets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tripSheets);
    }

    /**
     * Creates a new trip sheet for a specific vehicle deployment plan.
     *
     * @param vehicleDeploymentPlanId the ID of the vehicle deployment plan.
     * @param persons a set of PersonOutputDTOs representing the persons to be included in the trip sheet.
     * @return a ResponseEntity containing the created TripSheetOutputDTO or a bad request status.
     */
    @PostMapping("/vehicleDeploymentPlan/{vehicleDeploymentPlanId}")
    public ResponseEntity<TripSheetOutputDTO> createTripSheet(@PathVariable Long vehicleDeploymentPlanId, @RequestBody Set<PersonOutputDTO> persons) {
        if(persons == null || persons.isEmpty() || !tripSheetService.existsPlan(vehicleDeploymentPlanId))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(tripSheetService.createTripSheet(vehicleDeploymentPlanId, persons));
    }

    /**
     * Deletes a trip sheet by its ID.
     *
     * @param id the ID of the trip sheet.
     * @return a ResponseEntity with an ok deletion status or a not found status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTripSheet(@PathVariable Long id) {
        if(!tripSheetService.existsTripSheet(id))
            return ResponseEntity.notFound().build();
        tripSheetService.deleteTripSheet(id);
        return ResponseEntity.ok().build();
    }
}
