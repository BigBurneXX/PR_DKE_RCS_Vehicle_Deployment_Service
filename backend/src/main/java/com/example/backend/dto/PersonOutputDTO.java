package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data Transfer Object for person output.
 */
@Data
@NoArgsConstructor
public class PersonOutputDTO {
    private Long id;
    private String name;
    private Date creationDate;
    private Date lastModifiedDate;
    private Long version;
    private Long personId;
    private boolean hasWheelchair;
    private LocationDTO startLocation;
    private LocationDTO endLocation;
}
