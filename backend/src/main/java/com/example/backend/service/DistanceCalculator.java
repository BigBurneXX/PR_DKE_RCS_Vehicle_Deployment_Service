package com.example.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.List;

public class DistanceCalculator {
    private static final String API_KEY = "5b3ce3597851110001cf62484632c7a923954e39bbdf8f1224470ce7";
    private static final String API_URL = "https://api.openrouteservice.org/v2/directions/driving-car";
    public static void main(String[] args) {
        double sourceLat = 52.520008;
        double sourceLon = 13.404954;
        double destLat = 51.507351;
        double destLon = -0.127758;

        Map<String, Object> requestBodyMap = Map.of(
                "coordinates", List.of(
                        List.of(sourceLon, sourceLat),
                        List.of(destLon, destLat)
                ),
                "format", "geojson"
        );

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", API_KEY)
                    .header("Content-Type", "application/json")
                    //.header("Accept", "application/geo+json;charset=UTF-8")
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            // Parse the response JSON to get the road distance
            // Example: {"routes":[{"summary":{"distance":93420.3,"duration":8390.1}}]}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
