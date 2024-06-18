package com.example.backend.service;

import com.example.backend.model.Location;
import com.example.backend.response.DistanceMatrixResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DistanceMatrixService {

    @Value("${openrouteservice.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

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
    }
}
