package com.alga.algasensors.temperature.monitoring.domain.service;

import com.alga.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorId;
import com.alga.algasensors.temperature.monitoring.domain.repository.SensorAlertRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SensorAlertService {

    private static final Logger log = LoggerFactory.getLogger(SensorAlertService.class);
    private final SensorAlertRepository sensorAlertRepository;

    @Transactional
    public void handleAlert(TemperatureLogData temperatureLogData) {
        sensorAlertRepository.findById(new SensorId(temperatureLogData.getSensorId()))
                .ifPresentOrElse(alert -> {
                    if(alert.getMaxTemperature() != null &&
                    temperatureLogData.getValue().compareTo(alert.getMaxTemperature()) >= 0
                    ) {
                        log.info("Alert SensorId {}, Temperature MAX: {}", temperatureLogData.getSensorId(), alert.getMaxTemperature());
                    } else if (alert.getMinTemperature() != null &&
                            temperatureLogData.getValue().compareTo(alert.getMinTemperature()) <= 0) {
                        log.info("Alert SensorId {}, Temperature MIN: {}", temperatureLogData.getSensorId(), alert.getMinTemperature());
                    } else {
                        getSensorNotFound();
                    }

                }, SensorAlertService::getSensorNotFound);
    }

    private static void getSensorNotFound() {
        log.info("Sensor not found");
    }


}
