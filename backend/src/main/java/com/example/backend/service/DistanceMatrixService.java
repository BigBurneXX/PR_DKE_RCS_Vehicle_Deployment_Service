package com.example.backend.service;

import com.example.backend.model.Location;
import com.example.backend.response.DistanceMatrixResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class DistanceMatrixService {

    @Value("${openrouteservice.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public DistanceMatrixResponse getDistanceMatrix(Location location1, Location location2) {
        String url = "https://api.openrouteservice.org/v2/matrix/driving-car";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("locations", new double[][]{
                {location1.getLongitude(), location1.getLatitude()},
                {location2.getLongitude(), location2.getLatitude()}
        });

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList(apiKey));
        headers.put("Content-Type", Collections.singletonList("application/json"));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make HTTP POST request to OpenRouteService Distance Matrix API
        ResponseEntity<DistanceMatrixResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, DistanceMatrixResponse.class);

        return response.getBody();
    }

    /*
    public DistanceMatrixResponse getDistanceMatrix(Location location1, Location location2) {
        double[] originsLat = new double[]{location1.getLatitude()};
        double[] originsLon = new double[]{location1.getLongitude()};
        double[] destinationsLat = new double[]{location2.getLatitude()};
        double[] destinationsLon = new double[]{location2.getLongitude()};

        // Construct URL for the distance matrix API
        String url = "https://api.openrouteservice.org/v2/matrix/driving-car?" +
                "locations=" + buildCoordinatesString(originsLat, originsLon) +
                "|" + buildCoordinatesString(destinationsLat, destinationsLon) +
                "&api_key=" + apiKey;

        // Make HTTP GET request to OpenRouteService Distance Matrix API
        return restTemplate.getForObject(url, DistanceMatrixResponse.class);
    }

    private String buildCoordinatesString(double[] latitudes, double[] longitudes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < latitudes.length; i++) {
            sb.append(longitudes[i]).append(",").append(latitudes[i]);
            if (i < latitudes.length - 1) {
                sb.append("|");
            }
        }
        return sb.toString();
    }*/
}
