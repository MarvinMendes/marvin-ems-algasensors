package com.alga.algasensors.device.management.domain.repository;

import com.alga.algasensors.device.management.domain.model.SensorId;
import com.alga.algasensors.device.management.domain.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}
