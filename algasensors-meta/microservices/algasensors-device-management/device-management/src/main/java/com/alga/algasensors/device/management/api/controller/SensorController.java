package com.alga.algasensors.device.management.api.controller;

import com.alga.algasensors.device.management.api.model.SensorInput;
import com.alga.algasensors.device.management.api.model.SensorOutput;
import com.alga.algasensors.device.management.common.IdGenerator;
import com.alga.algasensors.device.management.domain.model.Sensor;
import com.alga.algasensors.device.management.domain.model.SensorId;
import com.alga.algasensors.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        return convert(sensor);
    }

    private SensorOutput convert(Sensor sensor) {
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

    @GetMapping("{sensorId}")
    public SensorOutput getOne(@PathVariable TSID sensorId) {
        Sensor sensor = repository.findById(new SensorId(sensorId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return convert(sensor);
    }

    @GetMapping
    public Page<SensorOutput> search(@PageableDefault Pageable pageable) {
        Page<Sensor> sensorPage = repository.findAll(pageable);

        return sensorPage.map(this::convert);

    }



}
