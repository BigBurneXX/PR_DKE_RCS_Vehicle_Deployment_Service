package com.example.backend.dto;

/**
 * Data Transfer Object for person input.
 */
public record PersonInputDTO(Long id, String first_name, String last_name, String date_of_birth, Long start_address,
                             Long target_address, boolean has_wheelchair) {
}
