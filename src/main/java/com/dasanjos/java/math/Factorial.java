package com.dasanjos.java.math;

/**
 * Recursive and Iteractive algorithms for Factorial calculation. Note that
 * Integer primitives can handle factorials up to 13!, long can handle up to 25!
 * and double up to 170!, which is the largest factorial whose floating-point
 * approximation can be represented as a 64-bit IEEE 754 floating-point value.<br>
 * Complexity: O(n)
 */
public class Factorial {

	public static double recursive(double n) {
		return (n <= 1) ? 1 : n * recursive(n - 1);
	}

	public static double iterative(double n) {
		double fac = 1;
		for (int i = 1; i <= n; i++) {
			fac *= i;
		}
		return fac;
	}
}
