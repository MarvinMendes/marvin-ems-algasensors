package com.alga.algasensors.temperature.monitoring.domain.repository;

import com.alga.algasensors.temperature.monitoring.domain.model.SensorAlert;
import com.alga.algasensors.temperature.monitoring.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorAlertRepository extends JpaRepository<SensorAlert, SensorId> {
}
