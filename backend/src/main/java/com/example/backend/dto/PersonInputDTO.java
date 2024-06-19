package com.example.backend.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class PersonInputDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Long startAddressId;
    private Long targetAddressId;
    private boolean hasWheelChair;
}
