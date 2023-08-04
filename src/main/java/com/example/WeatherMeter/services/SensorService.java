package com.example.WeatherMeter.services;

import com.example.WeatherMeter.models.Sensor;
import com.example.WeatherMeter.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Sensor> findSensorByName(String name) {
        return sensorRepository.findSensorByName(name);
    }

    @Transactional
    public void save(Sensor sensorToSave) {
        sensorRepository.save(sensorToSave);
    }
}
