package com.example.backend.dto;

/**
 * Data Transfer Object for address input.
 */
public record AddressInputDTO(Long id, String street, String house_no, Long town, String latitude, String longitude) {
}
