package com.alga.algasensors.temperature.monitoring.infraestructure.rabbimq;

import com.alga.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import com.alga.algasensors.temperature.monitoring.domain.service.SensorAlertService;
import com.alga.algasensors.temperature.monitoring.domain.service.TemperatureMonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


import static com.alga.algasensors.temperature.monitoring.infraestructure.rabbimq.RabbitMQConfig.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final TemperatureMonitoringService temperatureMonitoringService;
    private final SensorAlertService sensorAlertService;

    @RabbitListener(queues = QUEUE_PROCESS_TEMPERATURE, concurrency = "2-3")
    @SneakyThrows
    public void handleProcess(@Payload TemperatureLogData temperatureLogData) {
        temperatureMonitoringService.processTemperatureReading(temperatureLogData);
    }

    @RabbitListener(queues = QUEUE_ALERTING, concurrency = "2-3")
    @SneakyThrows
    public void handleAlerting(@Payload TemperatureLogData temperatureLogData) {
        sensorAlertService.handleAlert(temperatureLogData);
    }

}
