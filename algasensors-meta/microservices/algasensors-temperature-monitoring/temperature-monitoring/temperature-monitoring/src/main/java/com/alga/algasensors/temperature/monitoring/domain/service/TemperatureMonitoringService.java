package com.alga.algasensors.temperature.monitoring.domain.service;

import com.alga.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorId;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.alga.algasensors.temperature.monitoring.domain.model.TemperatureLog;
import com.alga.algasensors.temperature.monitoring.domain.model.TemperatureLogId;
import com.alga.algasensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import com.alga.algasensors.temperature.monitoring.domain.repository.TemperatureLogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class TemperatureMonitoringService {

    private static final Logger log = LoggerFactory.getLogger(TemperatureMonitoringService.class);
    private final SensorMonitoringRepository sensorMonitoringRepository;
    private final TemperatureLogRepository temperatureLogRepository;

    @Transactional
    public void processTemperatureReading(TemperatureLogData temperatureLogData) {
        sensorMonitoringRepository.findById(new SensorId(temperatureLogData.getSensorId())).ifPresentOrElse(
                sensor -> handleSensorMonitoring(temperatureLogData, sensor), () -> logIgnoredTemperature(temperatureLogData)
        );
    }

    private void logIgnoredTemperature(TemperatureLogData temperatureLogData) {

        log.info("Temperature Ignored: SensorId {} Temp {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());

    }

    private void handleSensorMonitoring(TemperatureLogData temperatureLogData, SensorMonitoring sensor) {
        if(sensor.isEnable()) {
            sensor.setLastTemperature(temperatureLogData.getValue());
            sensor.setUpdateAt(OffsetDateTime.now());
            sensorMonitoringRepository.save(sensor);

            TemperatureLog temperatureLog = TemperatureLog.builder()
                    .id(new TemperatureLogId(temperatureLogData.getId()))
                    .registeredAt(temperatureLogData.getRegisteredAt())
                    .value(temperatureLogData.getValue())
                    .sensorId(new SensorId(temperatureLogData.getSensorId()))
                    .build();

            temperatureLogRepository.save(temperatureLog);
        } else  {
            logIgnoredTemperature(temperatureLogData);
        }
    }
}
