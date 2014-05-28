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

	public static String oddNumbers(int upToThis) {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i < upToThis; i += 2)
			result.append(String.format("%3d", i));

		return result.toString();
	}

	public static int findLargest(int[] input) {
		int largest = Integer.MIN_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (input[i] > largest)
				largest = input[i];
		}
		return largest;
	}

	public static String hexToRgb(String hex) {
		int r = Integer.decode("0x" + hex.substring(0, 2));
		int g = Integer.decode("0x" + hex.substring(2, 4));
		int b = Integer.decode("0x" + hex.substring(4, 6));
		return String.format("%3d, %3d, %3d", r, g, b);
	}

	public static String rgbToHex(int r, int g, int b) {
		return String.format("%02X%02X%02X", r, g, b);
	}
}
