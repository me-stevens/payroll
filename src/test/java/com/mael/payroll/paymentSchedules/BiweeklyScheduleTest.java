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
	public void setUp() throws Exception {
		biweeklySchedule = new BiweeklySchedule();
	}

	@Test
	public void itIsPayDayIfItsFridayAndBiweekly() {
		LocalDate fridayAndBiweekly = of(2016, JANUARY, 29);
		assertTrue(biweeklySchedule.isPayDay(fridayAndBiweekly));
	}

	@Test
	public void isNotPayDayIfItsNotFriday() {
		LocalDate notAFridayAndBiweekly = of(2016, JANUARY, 30);
		assertFalse(biweeklySchedule.isPayDay(notAFridayAndBiweekly));
	}

	@Test
	public void isNotPayDayIfItsNotBiweekly() {
		LocalDate fridayAndNotBiweekly = of(2016, JANUARY, 22);
		assertFalse(biweeklySchedule.isPayDay(fridayAndNotBiweekly));
	}
}
