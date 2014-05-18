package com.dasanjos.java.search;

/**
 * Binary search is a divide and conquer search algorithm that halves the number
 * of items to check with each iteration, so locating an item (or determining
 * its absence) takes logarithmic time. This is a recursive implementation that
 * returns the value index on the ordered list (input) or null if not found. <br>
 * Complexity: Worst case O(logn), Best case O(1), Average case O(logn)
 */
public class BinarySearch {

	public static Integer search(int[] input, int value) {
		return search(input, value, 0, input.length);
	}

	private static Integer search(int[] input, int value, int start, int end) {
		if (start >= end) {
			return null;
		}

		int lenght = end - start;
		if (lenght <= 1) {
			if (value == input[start]) {
				return start;
			} else {
				return null;
			}
		}

		int middle = start + (lenght / 2);
		if (value == input[middle]) {
			return middle;
		} else if (value < input[middle]) {
			return search(input, value, start, middle);
		} else {
			return search(input, value, middle + 1, end);
		}
	}

	private static Integer search() {
		// TODO Iteractive version
		return null;
	}
}
