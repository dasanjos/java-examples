package com.dasanjos.java;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class FibonacciTest {

	int[] fib = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
			987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025,
			121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578,
			5702887, 9227465, 14930352, 24157817, 39088169 };

	@Rule
	public TestRule watchman = new TestWatcher() {
		long startTime = 0;
		long endTime = 0;

		@Override
		protected void starting(Description description) {
			super.starting(description);
			startTime = System.currentTimeMillis();
		}

		@Override
		protected void finished(Description description) {
			super.finished(description);
			endTime = System.currentTimeMillis();
			System.out.printf("%s time: %dns%n", description.getMethodName(), endTime - startTime);

		}
	};

	@Test
	public void calculateFibonacciNumbersWithRecursion() {
		for (int i = 0; i < fib.length; i++) {
			Assert.assertEquals(fib[i], Fibonacci.fibNumber(i));
		}
	}

	@Test
	public void calculateFibonacciNumbersDynamicly() {
		for (int i = 0; i < fib.length; i++) {
			Assert.assertEquals(fib[i], Fibonacci.fibNumberDynamic(i));
		}
	}

	@Test
	public void calculateFibonacciSum() {
		long sum = 0;
		for (int i = 1; i < fib.length; i++) {
			sum += fib[i];
			Assert.assertEquals(sum, Fibonacci.fibSum(i), 0.1);
		}
	}

	@Test
	public void calculateAverageFibonacci() {
		long sum = 0;
		for (int i = 1; i < fib.length; i++) {
			sum += fib[i];
			Assert.assertEquals(sum / i, Fibonacci.fibAverage(i), 0.1);
		}
	}
}
