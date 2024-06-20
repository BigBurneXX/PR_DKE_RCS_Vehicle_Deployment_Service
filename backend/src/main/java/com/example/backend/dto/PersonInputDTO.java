package com.example.backend.dto;

import lombok.*;

public record PersonInputDTO(Long id, String firstName, String lastName, String dateOfBirth, Long startAddress,
                             Long targetAddress, boolean hasWheelchair) {
}
