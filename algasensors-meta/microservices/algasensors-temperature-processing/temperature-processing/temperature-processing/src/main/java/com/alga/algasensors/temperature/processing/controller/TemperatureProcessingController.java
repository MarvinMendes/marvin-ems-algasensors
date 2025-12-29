package com.alga.algasensors.temperature.processing.controller;

import com.alga.algasensors.temperature.processing.common.IdGenerator;
import com.alga.algasensors.temperature.processing.model.TemeperatureLogOutput;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.time.OffsetDateTime;

import static com.alga.algasensors.temperature.processing.infraestructure.rabbitmq.RabbitMQConfig.FANOUT_EXCHANGE_NAME;

@RestController
@Slf4j
@RequestMapping("/api/sensors/{sensorId}/temperatures/data")
@RequiredArgsConstructor
public class TemperatureProcessingController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void data(@PathVariable TSID sensorId, @RequestBody String input) {
        if(input == null || input.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Double temperature;

        try {
            temperature = Double.parseDouble(input);
        } catch (NumberFormatException e)  {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        TemeperatureLogOutput logOutput = TemeperatureLogOutput.builder()
                .id(IdGenerator.generateTimeBasedUUID())
                .sensorId(sensorId)
                .value(temperature)
                .registeredAt(OffsetDateTime.now())
                .build();

        String exchange = FANOUT_EXCHANGE_NAME;
        String routingKey = "";
        TemeperatureLogOutput payload = logOutput;

        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setHeader("sensorId", logOutput.getSensorId().toString());
            return message;
        };

        rabbitTemplate.convertAndSend(exchange, routingKey, payload, messagePostProcessor);

        log.info(logOutput.toString());

    }


}
