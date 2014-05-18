package com.dasanjos.java.sort;

import com.dasanjos.java.dataStructure.BinaryHeap;

/**
 * Heap Sort is a selection sort (in-place) algorithm that uses a Binary Heap to
 * create a logarithmic-time priority queue, giving a favorable worst-case O(n
 * log n) runtime. <br>
 * Complexity: Worst case O(nlogn), Best case O(nlogn), Average case O(nlogn),
 * Space O(n)
 */
public class HeapSort {

	public static void sort(int[] input) {
		// TODO Replace by buildHeap method - O(n)
		BinaryHeap heap = new BinaryHeap(input.length);
		for (int i = 0; i < input.length; i++) {
			heap.insert(input[i]);
		}

		for (int i = 0; i < input.length; i++) {
			input[i] = heap.deleteMin();
		}
	}

}
