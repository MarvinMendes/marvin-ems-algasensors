package com.alga.algasensors.device.management.api.controller;

import com.alga.algasensors.device.management.api.model.SensorInput;
import com.alga.algasensors.device.management.api.model.SensorOutput;
import com.alga.algasensors.device.management.common.IdGenerator;
import com.alga.algasensors.device.management.domain.model.Sensor;
import com.alga.algasensors.device.management.domain.model.SensorId;
import com.alga.algasensors.device.management.domain.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput input) {
        Sensor sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enable(false)
                .build();
        repository.saveAndFlush(sensor);
        return SensorOutput.builder()
                .id(sensor.getId().getSensorId())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .model(sensor.getModel())
                .protocol(sensor.getProtocol())
                .enable(sensor.getEnable())
                .build();
    }

}
