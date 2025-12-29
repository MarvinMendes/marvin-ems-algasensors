package com.alga.algasensors.temperature.monitoring.domain.repository;

import com.alga.algasensors.temperature.monitoring.domain.model.SensorId;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId> {
}
