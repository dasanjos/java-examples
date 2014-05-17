package com.dasanjos.java.sort;

/**
 * Merge Sort is a divide and conquer sorting algorithm that works by repeatedly
 * dividing the list to be sorted until it's an unitary list and merging it back
 * in order.<br>
 * Complexity: Worst case O(nlogn), Best case O(n), Average case O(nlogn)
 */

public class MergeSort {

	int[] tmp;

	public void mergeSort(int[] input) {
		tmp = new int[input.length];
		mergeSort(input, 0, input.length - 1);
	}

	public void mergeSort(int[] input, int start, int end) {
		if (start < end) {
			int middle = start + (end - start) / 2;
			mergeSort(input, start, middle);
			mergeSort(input, middle + 1, end);
			// merge two arrays
			merge(input, start, middle, end);
		}

	}

	private void merge(int[] input, int start, int middle, int end) {
		for (int i = start; i <= end; i++) {
			tmp[i] = input[i];
		}

		int i = start;
		int j = middle + 1;
		int k = start;

		while (i <= middle && j <= end) {
			if (tmp[i] < tmp[j]) {
				input[k++] = tmp[i++];
			} else {
				input[k++] = tmp[j++];
			}
		}
		while (i <= middle) {
			input[k++] = tmp[i++];
		}
	}
}
