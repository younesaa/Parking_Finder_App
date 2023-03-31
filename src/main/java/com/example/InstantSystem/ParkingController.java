package com.example.InstantSystem;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    private final String API_URL = "https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3";
    private final String API_AVAILABLE_SPACES_URL = "https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel&facet=nom";

    @GetMapping
    public ResponseEntity<ParkingResponse> getParkings() throws IOException, InterruptedException {
        List<ParkingRecord> parkings = new ArrayList<>();

        //Récupération des parkings
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray records = jsonObject.getJSONArray("records");

        //Récupération des places disponibles
        HttpRequest requestAvailableSpaces = HttpRequest.newBuilder()
                .uri(URI.create(API_AVAILABLE_SPACES_URL))
                .build();
        HttpResponse<String> responseAvailableSpaces = client.send(requestAvailableSpaces, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObjectAvailableSpaces = new JSONObject(responseAvailableSpaces.body());
        JSONArray recordsAvailableSpaces = jsonObjectAvailableSpaces.getJSONArray("records");

        //Mapping des informations des parkings et places disponibles
        for (int i = 0; i < records.length(); i++) {
            JSONObject record = records.getJSONObject(i);
            JSONObject fields = record.getJSONObject("fields");

            String name = fields.getString("nom_du_parking");
            String address = fields.getString("adresse");
            double latitude = fields.getDouble("latitude");
            double longitude = fields.getDouble("longitude");

            int availableSpaces = 0;
            for (int j = 0; j < recordsAvailableSpaces.length(); j++) {
                JSONObject recordAvailableSpaces = recordsAvailableSpaces.getJSONObject(j);
                JSONObject fieldsAvailableSpaces = recordAvailableSpaces.getJSONObject("fields");

                String parkingName = fieldsAvailableSpaces.getString("nom");
                if (parkingName.equals(name)) {
                    availableSpaces += fieldsAvailableSpaces.getInt("disponible");
                }
            }

            ParkingRecord parkingRecord = new ParkingRecord(name, address, latitude, longitude, availableSpaces);
            parkings.add(parkingRecord);
        }

        ParkingResponse responseDTO = new ParkingResponse(parkings);
        return ResponseEntity.ok().body(responseDTO);
    }
}