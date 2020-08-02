package com.github.mouftizo.coronavirustracker.services;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


public class WeatherData {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String fetchData(String lat, String lon) throws IOException, InterruptedException {
        String apiKey = "79aa2ad01f02136d0707a4952d47b7aa";
        String WEATHER_URL = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s", lat, lon, apiKey);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(WEATHER_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonResponse = response.body();

        Gson g = new Gson();
        var json = g.fromJson(jsonResponse, Map.class);

        return g.toJsonTree(json)
                .getAsJsonObject().get("weather")
                .getAsJsonArray().get(0)
                .getAsJsonObject().get("description")
                .getAsString();
    }

}
