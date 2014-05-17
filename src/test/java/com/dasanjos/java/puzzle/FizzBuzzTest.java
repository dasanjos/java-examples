package com.dasanjos.java.puzzle;

import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzTest {

	@Test
	public void testFizzBuzzCalculation() {
		Assert.assertEquals("1\n", FizzBuzz.calculate(1));
		Assert.assertEquals("2\n", FizzBuzz.calculate(2));
		Assert.assertEquals("Fizz\n", FizzBuzz.calculate(3));
		Assert.assertEquals("4\n", FizzBuzz.calculate(4));
		Assert.assertEquals("Buzz\n", FizzBuzz.calculate(5));
		Assert.assertEquals("FizzBuzz\n", FizzBuzz.calculate(15));
	}

}
