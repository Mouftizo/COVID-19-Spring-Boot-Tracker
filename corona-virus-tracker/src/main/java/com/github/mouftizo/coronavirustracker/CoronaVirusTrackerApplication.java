package com.github.mouftizo.coronavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class CoronaVirusTrackerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(CoronaVirusTrackerApplication.class, args);
    }

}
