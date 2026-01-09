package com.alga.algasensors.temperature.monitoring.api.controller;

import com.alga.algasensors.temperature.monitoring.api.model.SensorMonitoringOutput;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorId;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.alga.algasensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")
@RequiredArgsConstructor
public class SensorMonitoringController {

    private final SensorMonitoringRepository repository;

    @GetMapping
    public SensorMonitoringOutput getDetail(@PathVariable TSID sensorId) {
        SensorMonitoring sensorMonitoring = findById(sensorId);
        return SensorMonitoringOutput.builder()
                .id(sensorId)
                .enabled(sensorMonitoring.getEnabled())
                .lastTemperature(sensorMonitoring.getLastTemperature())
                .updateAt(sensorMonitoring.getUpdateAt())
                .build();
    }

    
    private SensorMonitoring findById(@PathVariable TSID sensorId) {
        return repository.findById(new SensorId(sensorId)).orElse(SensorMonitoring.builder()
                .id(new SensorId(sensorId))
                .enabled(true)
                .lastTemperature(null)
                .updateAt(null)
                .build());
    }

    @PutMapping("/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable TSID sensorId) {
        SensorMonitoring sensorMonitoring = findById(sensorId);
        sensorMonitoring.setEnabled(true);
        sensorMonitoring.setUpdateAt(OffsetDateTime.now());
        repository.saveAndFlush(sensorMonitoring);
    }

    @DeleteMapping("/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {
        SensorMonitoring sensorMonitoring = findById(sensorId);
        sensorMonitoring.setEnabled(false);
        sensorMonitoring.setUpdateAt(OffsetDateTime.now());
        repository.saveAndFlush(sensorMonitoring);
    }





}
