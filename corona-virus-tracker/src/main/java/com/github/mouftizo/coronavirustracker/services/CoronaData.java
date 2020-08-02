package com.github.mouftizo.coronavirustracker.services;

import com.github.mouftizo.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaData {

    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();

    @PostConstruct // Execute after app starts
    @Scheduled(cron = "* * * 1 * *")
    public void fetchData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader = new StringReader(response.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            var state = record.get("Province/State");
            var country = record.get("Country/Region");
            var latestTotal = Integer.parseInt(record.get(record.size() - 1));
            var weatherDescription = WeatherData.fetchData(record.get("Lat"), record.get("Long"));
            var diffFromPrevious = Integer.parseInt(record.get(record.size() - 2));

            LocationStats locationStat = new LocationStats();
            locationStat.setState(state);
            locationStat.setCountry(country);
            locationStat.setLatestTotal(latestTotal);
            locationStat.setDescription(weatherDescription);
            locationStat.setDiffFromPreviousDay(latestTotal - diffFromPrevious);

            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }

    public List<LocationStats> getAllStats() {
        return allStats;
    }

}
