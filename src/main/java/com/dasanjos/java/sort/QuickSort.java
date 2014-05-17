package com.dasanjos.java.sort;

public class QuickSort {

	public static void quickSort(int[] input) {
		quickSort(input, 0, input.length - 1);
	}

	private static void quickSort(int[] input, int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = input[low + (high - low) / 2];

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (input[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (input[j] > pivot) {
				j--;
			}

			// If we have found a values in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				swap(input, i, j);
				i++;
				j--;
			}
		}
		// Recursion
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
