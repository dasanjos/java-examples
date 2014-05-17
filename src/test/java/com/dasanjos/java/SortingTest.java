package com.dasanjos.java;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.sort.BubbleSort;
import com.dasanjos.java.sort.MergeSort;
import com.dasanjos.java.util.TimeTracker;

public class SortingTest extends TimeTracker {

	int[] numbers;
	int SIZE = 10;

	@Before
	public void setup() {
		numbers = new int[SIZE];
		Random generator = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = generator.nextInt(SIZE);
		}
	}

	@Test
	public void testJavaSort() {
		Arrays.sort(numbers);
		verifySorting(numbers);
	}

	@Test
	public void testBubbleSort() {
		BubbleSort.sort(numbers);
		verifySorting(numbers);
	}

	@Test
	public void testMergeSort() {
		new MergeSort().mergeSort(numbers);
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
