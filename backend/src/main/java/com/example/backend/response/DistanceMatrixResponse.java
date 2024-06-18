package com.example.backend.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceMatrixResponse {
    private String[] destinations;
    private String[] sources;
    private double[][] durations; // In seconds
    private double[][] distances; // In meters
}
