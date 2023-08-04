package com.example.WeatherMeter.controllers;

import com.example.WeatherMeter.dto.SensorDTO;
import com.example.WeatherMeter.models.Sensor;
import com.example.WeatherMeter.services.SensorService;
import com.example.WeatherMeter.utils.sensorUtil.SensorErrorResponse;
import com.example.WeatherMeter.utils.sensorUtil.SensorNotCreatedException;
import com.example.WeatherMeter.utils.sensorUtil.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor validSensor = convertedDTOToSensor(sensorDTO);

        sensorValidator.validate(validSensor, bindingResult);

        if (bindingResult.hasErrors()) {

            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors =  bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotCreatedException(errorMessage.toString());
        }

        sensorService.save(validSensor);

        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    private Sensor convertedDTOToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        LocalDateTime errorTime = LocalDateTime.now();
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), errorTime);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);  // 400
    }
}
