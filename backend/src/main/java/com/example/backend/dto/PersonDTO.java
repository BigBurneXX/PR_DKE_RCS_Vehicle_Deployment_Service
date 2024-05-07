package com.example.backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private Long startAddressId;

    private Long targetAddressId;

    private boolean hasWheelChair;
}
