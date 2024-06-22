package com.example.backend.dto;

public record AddressDTO (Long id, String street, String houseNo, Long townId, String latitude, String longitude) {
}
