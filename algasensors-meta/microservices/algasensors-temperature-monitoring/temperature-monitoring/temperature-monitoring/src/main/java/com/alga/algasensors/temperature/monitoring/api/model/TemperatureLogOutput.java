package com.alga.algasensors.temperature.monitoring.api.model;

import com.alga.algasensors.temperature.monitoring.domain.model.SensorId;
import com.alga.algasensors.temperature.monitoring.domain.model.TemperatureLogId;
import io.hypersistence.tsid.TSID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class TemperatureLogOutput {

    private UUID id;

    private Double value;

    private OffsetDateTime registeredAt;

    private TSID sensorId;

}
