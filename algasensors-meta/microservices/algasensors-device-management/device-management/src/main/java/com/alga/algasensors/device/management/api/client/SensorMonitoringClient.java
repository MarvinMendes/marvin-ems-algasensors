package com.alga.algasensors.device.management.api.client;

import com.alga.algasensors.device.management.api.model.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {

    void enableMonitoring(TSID tsid);

    void disableMonitoring(TSID tsid);

    SensorMonitoringOutput getDetail(TSID sensorId);
}
