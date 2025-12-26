package com.alga.algasensors.device.management.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorInput {
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;

}
