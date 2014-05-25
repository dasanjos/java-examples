package com.dasanjos.java.math;

public class MathHelper {

	public static String multiplicationTable(int number) {
		StringBuilder result = new StringBuilder();

		for (int j = 1; j <= number; j++) {
			for (int i = 1; i <= number; i++) {
				result.append(String.format("%4d", j * i));
			}
			result.append("\n");
		}

		return result.toString();
	}
}
