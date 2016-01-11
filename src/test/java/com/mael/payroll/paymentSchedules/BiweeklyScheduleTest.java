package com.mael.payroll.paymentSchedules;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.time.Month.JANUARY;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BiweeklyScheduleTest {

	private PaymentSchedule biweeklySchedule;

	@Before
	public void setUp() {
		biweeklySchedule = new BiweeklySchedule();
	}

	@Test
	public void itIsPayDayIfItIsFridayAndBiweekly() {
		LocalDate fridayAndBiweekly = of(2016, JANUARY, 22);
        assertTrue(biweeklySchedule.isPayDay(fridayAndBiweekly));
	}

	@Test
	public void itIsNotPayDayIfItIsNotFriday() {
		LocalDate notAFridayAndBiweekly = of(2016, JANUARY, 23);
        assertFalse(biweeklySchedule.isPayDay(notAFridayAndBiweekly));
	}

	@Test
	public void itIsNotPayDayIfItIsNotBiweekly() {
		LocalDate fridayAndNotBiweekly = of(2016, JANUARY, 29);
        assertFalse(biweeklySchedule.isPayDay(fridayAndNotBiweekly));
	}
}
