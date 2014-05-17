package com.dasanjos.java.sort;

/**
 * Bubble sort is a simple sorting algorithm that works by repeatedly stepping
 * through the list to be sorted, comparing each pair of adjacent items and
 * swapping them if they are in the wrong order. <br>
 * Complexity: Worst case O(n^2), Best case O(n), Average case O(n^2)
 */
public class BubbleSort {

	public static void sort(int[] input) {
		for (int i = 0; i < input.length - 1; i++) {
			for (int j = i; j < input.length; j++) {
				if (input[i] > input[j]) {
					swapInPlace(input, i, j);
				}
			}
		}
	}

	static void swap(int[] input, int i, int j) {
		int swap;
		swap = input[i];
		input[i] = input[j];
		input[j] = swap;
	}

	static void swapInPlace(int[] input, int i, int j) {
		input[i] = input[i] + input[j];
		input[j] = input[i] - input[j];
		input[i] = input[i] - input[j];
	}
}
