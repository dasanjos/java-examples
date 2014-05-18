package com.dasanjos.java.math;

import org.junit.Assert;
import org.junit.Test;

import com.dasanjos.java.util.TimeTracker;

public class FibonacciTest extends TimeTracker {

	// First 40 Fibonacci numbers (pre calculated values)
	int[] fib = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
			987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025,
			121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578,
			5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155 };

	@Test
	public void calculateFibonacciNumbersWithRecursion() {
		for (int i = 0; i < fib.length; i++) {
			Assert.assertEquals(fib[i], Fibonacci.recursive(i));
		}
	}

	@Test
	public void calculateFibonacciNumbersDynamicly() {
		for (int i = 0; i < fib.length; i++) {
			Assert.assertEquals(fib[i], Fibonacci.dynamic(i));
		}
	}

	@Test
	public void calculateFibonacciSum() {
		long sum = 0;
		for (int i = 1; i < fib.length; i++) {
			sum += fib[i];
			Assert.assertEquals(sum, Fibonacci.sum(i), 0.1);
		}
	}

	@Test
	public void calculateAverageFibonacci() {
		long sum = 0;
		for (int i = 1; i < fib.length; i++) {
			sum += fib[i];
			Assert.assertEquals(sum / i, Fibonacci.average(i), 0.1);
		}
	}
}
