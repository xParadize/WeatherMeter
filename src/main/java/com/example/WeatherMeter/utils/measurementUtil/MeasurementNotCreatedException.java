package com.example.WeatherMeter.utils.measurementUtil;

public class MeasurementNotCreatedException extends RuntimeException {
    public MeasurementNotCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
