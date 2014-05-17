package com.dasanjos.java.sort;

import org.junit.Assert;
import org.junit.Test;

import com.dasanjos.java.util.TimeTracker;

public class BubbleSortTest extends TimeTracker {

	@Test
	public void test() {
		int[] input = { 56, 69, 24, 71, 20, 87, 53, 16, 39, 35, 60, 82, 74, 57,
				1, 96, 81, 15, 64, 6, 30, 84, 11, 55, 21, 37, 17, 94, 52, 51,
				34, 13, 47, 48, 98, 45, 9, 26, 12, 41, 8, 7, 29, 38, 86, 31,
				90, 10, 14, 62, 22, 80, 27, 93, 75, 32, 4, 89, 68, 44, 65, 73,
				78, 3, 25, 19, 79, 85, 72, 18, 59, 77, 83, 40, 2, 70, 36, 46,
				66, 97, 33, 76, 42, 61, 23, 50, 91, 28, 58, 43, 5, 54, 95, 100,
				49, 63, 88, 92, 67, 99 };

		BubbleSort.sort(input);

		isSorted(input);
	}

	private boolean isSorted(int[] input) {
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i] > input[i + 1]) {
				Assert.fail("Not sorted");
				return false;
			}
		}
		return true;
	}
}
