package com.example.backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public record AddressDTO (Long id, String street, String houseNo, Long townId)
}
