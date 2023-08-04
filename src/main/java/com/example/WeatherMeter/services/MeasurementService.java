package com.example.WeatherMeter.services;

import com.example.WeatherMeter.models.Measurement;
import com.example.WeatherMeter.models.Sensor;
import com.example.WeatherMeter.repositories.MeasurementsRepository;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    @Transactional(readOnly = true)
    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurementToSave) {

        String sensorName = measurementToSave.getSensor().getName();
        Optional<Sensor> existingSensor = sensorService.findSensorByName(sensorName);
        existingSensor.ifPresent(measurementToSave::setSensor);

        measurementToSave.setSetAt(LocalDateTime.now());
        measurementsRepository.save(measurementToSave);
    }

    // Подсчёт дождливых дней
    @Transactional(readOnly = true)
    public Integer rainyDaysCount() {
        return measurementsRepository.countByRaining(true);
    }
}
