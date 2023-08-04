package com.example.WeatherMeter.utils.sensorUtil;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
