package com.alga.algasensors.temperature.monitoring.domain.repository;

import com.alga.algasensors.temperature.monitoring.domain.model.SensorId;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.alga.algasensors.temperature.monitoring.domain.model.TemperatureLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureLogRepository extends JpaRepository<TemperatureLog, SensorId> {
    Page<TemperatureLog> findAllBySensorId(SensorId sensorId, Pageable pageable);
}
