package com.dasanjos.java;

public class Fibonacci {

	public static int fibNumber(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fibNumber(n - 1) + fibNumber(n - 2);
		}
	}

	public static double fibAverage(int n) {
		return fibSum(n) / n;
	}

	public static long fibSum(int n) {
		long average = 0;
		long fibNr = 1;
		long fibOne = 0;
		long fibTwo = 1;

		for (int i = 1; i <= n; i++) {
			average += fibNr;
			fibNr = fibOne + fibTwo;
			fibOne = fibTwo;
			fibTwo = fibNr;
		}
		return average;
	}
}
