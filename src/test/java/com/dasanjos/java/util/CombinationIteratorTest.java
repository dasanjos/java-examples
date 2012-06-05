package com.dasanjos.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CombinationIteratorTest {

	@Test
	public void testCombinationTwoChars() {
		Character[] array = { '0', '1' };
		CombinationIterator<Character> permutator = new CombinationIterator<Character>(array);

		List<String> results = new ArrayList<String>();
		while (permutator.hasNext()) {
			results.add(Arrays.toString(permutator.next()));
		}

		Assert.assertEquals(4, results.size()); // n^r = 2^2 = 4
		Assert.assertEquals("[0, 0]", results.get(0));
		Assert.assertEquals("[0, 1]", results.get(1));
		Assert.assertEquals("[1, 0]", results.get(2));
		Assert.assertEquals("[1, 1]", results.get(3));
	}

	@Test
	public void testCombinationThreeCharsSizeTwo() {
		Character[] array = { 'a', 'b', 'c' };
		CombinationIterator<Character> permutator = new CombinationIterator<Character>(array, 2);

		List<String> results = new ArrayList<String>();
		while (permutator.hasNext()) {
			results.add(Arrays.toString(permutator.next()));
		}

		Assert.assertEquals(9, results.size()); // n^r = 3^2 = 9
		Assert.assertEquals("[a, a]", results.get(0));
		Assert.assertEquals("[a, b]", results.get(1));
		Assert.assertEquals("[a, c]", results.get(2));
		Assert.assertEquals("[b, a]", results.get(3));
		Assert.assertEquals("[b, b]", results.get(4));
		Assert.assertEquals("[b, c]", results.get(5));
		Assert.assertEquals("[c, a]", results.get(6));
		Assert.assertEquals("[c, b]", results.get(7));
		Assert.assertEquals("[c, c]", results.get(8));
	}

	@Test
	public void testCombinationThreeChars() {
		Character[] array = { 'a', 'b', 'c' };
		CombinationIterator<Character> permutator = new CombinationIterator<Character>(array);

		int counter = 0;
		while (permutator.hasNext()) {
			System.out.println(Arrays.toString(permutator.next()));
			counter++;
		}

		Assert.assertEquals(27, counter); // n^r = 3^3 = 27
	}

	@Test
	public void testCombinationFiveIntegersSizeThree() {
		Integer[] array = { 1, 2, 3, 4, 5 };
		CombinationIterator<Integer> permutator = new CombinationIterator<Integer>(array, 3);

		int counter = 0;
		while (permutator.hasNext()) {
			System.out.println(Arrays.toString(permutator.next()));
			counter++;
		}

		Assert.assertEquals(125, counter); // n^r = 5^3 = 125
	}
}
