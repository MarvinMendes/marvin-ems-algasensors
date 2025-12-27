package com.alga.algasensors.device.management.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sensor {

    @Id
    @Column(columnDefinition = "BIGINT", name = "sensorId")
    private SensorId id;
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
    private Boolean enable;
}
