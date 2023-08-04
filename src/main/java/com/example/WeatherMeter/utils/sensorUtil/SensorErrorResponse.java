package com.example.WeatherMeter.utils.sensorUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SensorErrorResponse {

    private String errorMessage;
    private LocalDateTime errorTime;
}
