package com.example.WeatherMeter.client;

import com.example.WeatherMeter.models.Measurement;
import com.example.WeatherMeter.models.Sensor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// Class to integrate with measurement service api
public class JavaClient {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select option: 1 - GET, 2 - POST --> ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                getMethod();
                break;
            case 2:
                postMethod();
                break;
            default:
                System.out.println("Incorrect option!");
                break;
        }
    }

    public static void getMethod() {
        RestTemplate restTemplateGet = new RestTemplate();
        String getMethodUrl = "http://localhost:8080/measurements";
        String getMethodResponse = restTemplateGet.getForObject(getMethodUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

            Object json = objectMapper.readValue(getMethodResponse, Object.class);
            String formattedResponse = objectWriter.writeValueAsString(json);

            System.out.println(formattedResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void postMethod() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the count of measurements --> ");
        int inputs = scanner.nextInt();
        int tempInputs = inputs;

        while (inputs > 0) {
            RestTemplate restTemplatePost = new RestTemplate();
            String postMethodUrl = "http://localhost:8080/measurements/add";
            Random random = new Random();

            // Intermediate variables
            double d = ThreadLocalRandom.current().nextDouble(-100, 100);
            String randSensorName = "sensor" + ThreadLocalRandom.current().nextInt(1, 11);

            Double value = Math.round(d * 10.0) / 10.0;
            Boolean raining = random.nextBoolean();
            Sensor sensor = new Sensor(randSensorName);

            HttpEntity<Measurement> request= new HttpEntity<>(new Measurement(value, raining, sensor));

            try {
                restTemplatePost.postForObject(postMethodUrl, request, String.class);
            } catch (HttpClientErrorException e) {
                System.out.println("Error during creating a measurement!");
                System.out.println(e.getMessage());
            }

            inputs--;
        }

        System.out.println(tempInputs + " measurement(s) added.");
    }
}
