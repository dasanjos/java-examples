package com.dasanjos.java;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {

	int[] fib = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025,
			121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169 };

	@Test
	public void calculateFibonacciNumbers() {
		for (int i = 0; i < fib.length; i++) {
			Assert.assertEquals(fib[i], Fibonacci.fibNumber(i));
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
		double sum = 0;
		for (int i = 1; i < fib.length; i++) {
			sum += fib[i];
			Assert.assertEquals(sum / i, Fibonacci.fibAverage(i), 0.1);
		}
	}
}
