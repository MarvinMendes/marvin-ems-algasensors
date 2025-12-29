package com.alga.algasensors.temperature.processing;

import com.alga.algasensors.temperature.processing.common.IdGenerator;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDv7TEst {

    @Test
    void shouldGeneraetUUIDv7() {
        TimeBasedEpochGenerator timeBasedEpochGenerator = Generators.timeBasedEpochGenerator();

        UUID uuid1 = IdGenerator.generateTimeBasedUUID();
        UUID uuid2 = IdGenerator.generateTimeBasedUUID();
        UUID uuid3 = IdGenerator.generateTimeBasedUUID();
        UUID uuid4 = IdGenerator.generateTimeBasedUUID();

        System.out.println(uuid1);
        System.out.println(uuid2);
        System.out.println(uuid3);
        System.out.println(uuid4);


    }

}
