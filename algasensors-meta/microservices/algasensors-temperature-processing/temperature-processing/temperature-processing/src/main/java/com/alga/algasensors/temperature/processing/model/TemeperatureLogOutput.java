package com.alga.algasensors.temperature.processing.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class TemeperatureLogOutput {

    private UUID id;
    private TSID sensorId;
    private OffsetDateTime registeredAt;
    private Double value;

}
