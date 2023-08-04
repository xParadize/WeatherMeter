package com.example.WeatherMeter.client;

import com.example.WeatherMeter.dto.MeasurementDTO;
import com.example.WeatherMeter.models.Measurement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//TODO: create the chart of measurements to days (90% done, but got exception in getAllDates(): Exception in thread "main"
// com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not
// supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling)

//public class MeasurementsChart {
//
//    public static RestTemplate restTemplateGet = new RestTemplate();
//    public static String getMethodUrl = "http://localhost:8080/measurements";
//
//    public static void main(String[] args) throws JsonProcessingException {
//        buildChart();
//    }
//
//    public static double[] getAllMeasurements() throws JsonProcessingException {
//
//        ResponseEntity<String> response = restTemplateGet.getForEntity(getMethodUrl, String.class);
//        String json = response.getBody();
//
//        Measurement[] measurements = new ObjectMapper().readValue(json, Measurement[].class);
//
//        double[] measurementsToAdd = new double[measurements.length];
//        for (int i = 0; i < measurements.length; i++) {
//            measurementsToAdd[i] = measurements[i].getValue();
//        }
//        return measurementsToAdd;
//    }
//
//    public static double[] getAllDates() throws JsonProcessingException {
//        ResponseEntity<String> response = restTemplateGet.getForEntity(getMethodUrl, String.class);
//        String json = response.getBody();
//
//        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//        Measurement[] measurements = objectMapper.readValue(json, Measurement[].class);
//
//        double[] datesToAdd = new double[measurements.length];
//        for (int i = 0; i < measurements.length; i++) {
//            datesToAdd[i] = measurements[i].getSetAt().getDayOfMonth();
//        }
//        return datesToAdd;
//    }
//
//    public static void buildChart() throws JsonProcessingException {
//        int numCharts = 1;
//
//        List<XYChart> charts = new ArrayList<>();
//
//        for (int i = 0; i < numCharts; i++) {
//            XYChart chart = new XYChartBuilder()
//                    .xAxisTitle("Number of measurement")
//                    .yAxisTitle("Temperature")
//                    .width(1200).
//                    height(800).
//                    build();
//            chart.getStyler().setYAxisMin(-100.0);
//            chart.getStyler().setYAxisMax(100.0);
//            XYSeries series = chart.addSeries("Degrees", getAllDates(), getAllMeasurements());
//            series.setMarker(SeriesMarkers.NONE);
//            charts.add(chart);
//        }
//        new SwingWrapper<>(charts).displayChartMatrix();
//    }
//}
