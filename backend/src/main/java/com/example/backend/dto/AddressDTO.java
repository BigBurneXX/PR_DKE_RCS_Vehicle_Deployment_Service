package com.example.backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;

    private String street;

    private String houseNo;

    private Long townId;
}
