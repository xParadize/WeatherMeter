package com.example.WeatherMeter.repositories;

import com.example.WeatherMeter.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    Integer countByRaining(Boolean isDayRainy);
}
