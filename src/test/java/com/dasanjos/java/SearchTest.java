package com.dasanjos.java;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.search.BinarySearch;
import com.dasanjos.java.util.TimeTracker;

public class SearchTest extends TimeTracker {

	static final int ARRAY_SIZE = 1000000;
	int[] numbers;
	int value;

	@Before
	public void setup() {
		value = new Random().nextInt(ARRAY_SIZE);
		numbers = new int[ARRAY_SIZE];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i;
		}
	}

	@Test
	public void javaSearch() {
		int result = Arrays.binarySearch(numbers, value);
		Assert.assertTrue(result > 0);
		Assert.assertEquals(value, numbers[result]);

	}

	@Test
	public void binarySearch() {
		int result = BinarySearch.search(numbers, value);
		Assert.assertNotNull(result);
		Assert.assertEquals(value, numbers[result]);
	}
}
