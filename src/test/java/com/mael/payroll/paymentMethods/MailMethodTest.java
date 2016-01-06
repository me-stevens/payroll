package com.mael.payroll.paymentMethods;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MailMethodTest {

    @Test
    public void createsAMailMethod() {
        MailMethod mailMethod = new MailMethod("address");
        assertEquals("address", mailMethod.getAddress());
    }
}
