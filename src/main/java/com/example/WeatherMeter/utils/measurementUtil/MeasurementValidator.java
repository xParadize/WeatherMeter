package com.example.WeatherMeter.utils.measurementUtil;

import com.example.WeatherMeter.models.Measurement;
import com.example.WeatherMeter.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Measurement measurement = (Measurement) target;
        String sensorName = measurement.getSensor().getName();

        if (sensorName.isEmpty()) {
            errors.rejectValue("sensor.name", "", "Name of sensor shouldn't be empty!");
        }

        if (sensorService.findSensorByName(sensorName).isEmpty()) {
            errors.rejectValue("sensor.name", "", "Sensor with this name doesn't exist!");
        }
    }
}
