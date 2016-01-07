package com.mael.payroll.paymentMethods;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DepositMethodTest {

    @Test
    public void hasBankAccount() {
        DepositMethod depositMethod = new DepositMethod("SquidBank", 1234);
        assertEquals("SquidBank", depositMethod.getBank());
        assertEquals(1234, depositMethod.getAccount());
    }
}
