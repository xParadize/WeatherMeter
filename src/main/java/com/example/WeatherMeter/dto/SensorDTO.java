package com.example.WeatherMeter.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SensorDTO {

    @NotEmpty(message = "Sensor name is empty!")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 symbols")
    @Column(name = "name")
    private String name;
}
