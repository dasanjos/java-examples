package com.dasanjos.java.sort;

/**
 * QuickSort is a Divide and Conquer (in-place) sorting algorithm that first
 * divides a large list into two smaller sub-lists (low and high elements) and
 * then recursively sort the sub-list using a pivot element. <br>
 * Complexity: Worst case O(n^2), Best case O(nlogn), Average case O(nlogn)
 */

public class QuickSort {

	public static void sort(int[] input) {
		quickSort(input, 0, input.length - 1);
	}

	private static void quickSort(int[] input, int low, int high) {
		int i = low, j = high;
		int pivot = input[low + (high - low) / 2];

		while (i <= j) {
			while (input[i] < pivot) {
				i++;
			}
			while (input[j] > pivot) {
				j--;
			}

			if (i <= j) {
				swap(input, i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			quickSort(input, low, j);
		if (i < high)
			quickSort(input, i, high);
	}

	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
}
