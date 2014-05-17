package com.dasanjos.java;

public class Fibonacci {

	private static int[] table = new int[100];

	/**
	 * Calculates Fibonacci number recursively - complexity: O(2^n)
	 */
	public static int fibNumber(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fibNumber(n - 1) + fibNumber(n - 2);
		}
	}

	/**
	 * Calculates Fibonacci number dynamically - complexity: O(n)
	 */
	public static int fibNumberDynamic(int n) {
		if (n <= 1) {
			return n;
		} else if (table[n] != 0) {
			return table[n];
		} else {
			return table[n] = fibNumberDynamic(n - 1) + fibNumberDynamic(n - 2);
		}
	}

	/**
	 * Calculates the average of n first Fibonacci numbers - complexity: O(n)
	 */
	public static double fibAverage(int n) {
		return fibSum(n) / n;
	}

	/**
	 * Calculates the sum of n first Fibonacci numbers - complexity: O(n)
	 */
	public static long fibSum(int n) {
		long sum = 0;
		long fibNr = 1;
		long fibOne = 0;
		long fibTwo = 1;

		for (int i = 1; i <= n; i++) {
			sum += fibNr;
			fibNr = fibOne + fibTwo;
			fibOne = fibTwo;
			fibTwo = fibNr;
		}
		return sum;
	}
}
