package com.mael.payroll.paymentMethods;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HoldMethodTest {

    @Test
    public void createsAHoldMethod() {
        HoldMethod holdMethod = new HoldMethod("address");
        assertEquals("address", holdMethod.getAddress());
    }
}
