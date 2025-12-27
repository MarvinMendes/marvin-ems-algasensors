package com.alga.algasensors.device.management.api.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorInput {
    @NonNull
    private String name;
    @NonNull
    private String ip;
    @NonNull
    private String location;
    @NonNull
    private String protocol;
    @NonNull
    private String model;
    @NonNull
    private Boolean enable;

}
