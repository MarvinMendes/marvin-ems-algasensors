package com.alga.algasensors.device.management;

import io.hypersistence.tsid.TSID;
import org.junit.jupiter.api.Test;

class TSIDTest {

    @Test
    void shouldGenerateTSID() {
        TSID tsid = TSID.Factory.getTsid();

        System.out.println(tsid);
        System.out.println(tsid.toLong());
    }


}
