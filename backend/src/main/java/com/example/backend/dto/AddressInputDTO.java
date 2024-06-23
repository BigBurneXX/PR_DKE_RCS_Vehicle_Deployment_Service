package com.example.backend.dto;

public record AddressInputDTO(Long id, String street, String houseNo, Long townId, String latitude, String longitude) {
}
