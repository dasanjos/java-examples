package com.dasanjos.java.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.util.TimeTracker;

public class FactorialTest extends TimeTracker {

	double[] fac = new double[170];

	@Before
	public void setup() {
		fac[0] = 1;
		for (int i = 1; i < fac.length; i++) {
			fac[i] = fac[i - 1] * i;
		}
	}

	@Test
	public void recursive() {
		for (int i = 0; i < fac.length; i++) {
			Assert.assertEquals(fac[i], Factorial.recursive(i), 1);
		}
	}

	@Test
	public void iterative() {
		for (int i = 0; i < fac.length; i++) {
			Assert.assertEquals(fac[i], Factorial.iterative(i), 1);
		}
	}
}
