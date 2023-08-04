package com.example.WeatherMeter.controllers;

import com.example.WeatherMeter.dto.MeasurementDTO;
import com.example.WeatherMeter.models.Measurement;
import com.example.WeatherMeter.services.MeasurementService;
import com.example.WeatherMeter.utils.ApiError;
import com.example.WeatherMeter.utils.measurementUtil.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.findAll().stream()
                .map(this::convertMeasurementToDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/add")
    public ResponseEntity<?> createMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        Measurement validMeasurement = convertMeasurementDTOToMeasurement(measurementDTO);
        measurementValidator.validate(validMeasurement, bindingResult);

        if (bindingResult.hasErrors()) {

            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> String.format("%s - %s", error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.joining(";"));

            ApiError apiError = new ApiError(errorMessage, LocalDateTime.now());

            return ResponseEntity.badRequest().body(apiError);
        }

        measurementService.save(validMeasurement);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    private MeasurementDTO convertMeasurementToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertMeasurementDTOToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    @GetMapping("/rainyDaysCount")
    public Integer rainyDaysCount() {
        return measurementService.rainyDaysCount();
    }
}
