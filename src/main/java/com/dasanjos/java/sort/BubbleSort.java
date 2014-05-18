package com.dasanjos.java.sort;

/**
 * BubbleSort is a simple sorting algorithm that works by repeatedly stepping
 * through the list to be sorted, comparing each pair of adjacent items and
 * swapping them if they are in the wrong order. The modified version stops if
 * no element is swapped in a full pass (list already sorted).<br>
 * Complexity: Worst case O(n^2), Best case O(n), Average case O(n^2)
 */
public class BubbleSort {

	public static void sort(int[] input) {
		for (int n = input.length; n > 0; n--) {
			for (int i = 0; i < n - 1; i++) {
				if (input[i] > input[i + 1]) {
					swap(input, i, i + 1);
				}
			}
		}
	}

	public static void sortModified(int[] input) {
		boolean swapped = true;
		for (int n = input.length; n > 0 && swapped; n--) {
			swapped = false;
			for (int i = 0; i < n - 1; i++) {
				if (input[i] > input[i + 1]) {
					swap(input, i, i + 1);
					swapped = true;
				}
			}
		}
	}

	static void swap(int[] input, int a, int b) {
		int temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}

	static void swapInPlace(int[] input, int i, int j) {
		input[i] = input[i] + input[j];
		input[j] = input[i] - input[j];
		input[i] = input[i] - input[j];
	}
}
