package com.dasanjos.java;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.sort.BubbleSort;
import com.dasanjos.java.sort.MergeSort;
import com.dasanjos.java.sort.QuickSort;
import com.dasanjos.java.util.TimeTracker;

public class SortingTest extends TimeTracker {

	static final int ARRAY_SIZE = 10000;
	int[] numbers;

	@Before
	public void setup() {
		numbers = new int[ARRAY_SIZE];
		Random generator = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = generator.nextInt(ARRAY_SIZE);
		}
	}

	@Test
	public void testJavaSort() {
		Arrays.sort(numbers);
		verifySorting(numbers);
	}

	@Test
	public void testBubbleSort() {
		BubbleSort.bubbleSort(numbers);
		verifySorting(numbers);
	}

	@Test
	public void testMergeSort() {
		new MergeSort().mergeSort(numbers);
		verifySorting(numbers);
	}

	@Test
	public void testQuickSort() {
		QuickSort.quickSort(numbers);
		verifySorting(numbers);
	}

	private void verifySorting(int[] input) {
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i] > input[i + 1]) {
				Assert.fail("Input not sorted");
			}
		}
	}
}
