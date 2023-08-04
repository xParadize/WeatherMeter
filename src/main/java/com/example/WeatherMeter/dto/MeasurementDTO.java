package com.example.WeatherMeter.dto;

import com.example.WeatherMeter.models.Sensor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class MeasurementDTO {

    @NotNull(message = "Measurement value shouldn't be empty!")
    @Range(min = -100, max = 100, message = "Measurement value should be between -100 and 100 degrees!")
    private Double value;

    private Boolean raining;

    private Sensor sensor;

    private LocalDateTime setAt;
}
