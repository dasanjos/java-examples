package com.dasanjos.java.util.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dasanjos.java.util.math.PermutationIterator;

public class PermutationIteratorTest {

	@Test
	public void testPermutationTwoChars() {
		Character[] array = { '0', '1' };
		PermutationIterator<Character> permutator = new PermutationIterator<Character>(array);

		List<String> results = new ArrayList<String>();
		while (permutator.hasNext()) {
			results.add(Arrays.toString(permutator.next()));
		}

		Assert.assertEquals(2, results.size()); // n! = 2! = 2
		Assert.assertEquals("[0, 1]", results.get(0));
		Assert.assertEquals("[1, 0]", results.get(1));
	}

	@Test
	public void testPermutationThreeChars() {
		Character[] array = { 'a', 'b', 'c' };
		PermutationIterator<Character> permutator = new PermutationIterator<Character>(array);

		List<String> results = new ArrayList<String>();
		while (permutator.hasNext()) {
			results.add(Arrays.toString(permutator.next()));
		}

		Assert.assertEquals(6, results.size()); // n! = 3! = 6
		Assert.assertEquals("[a, b, c]", results.get(0));
		Assert.assertEquals("[a, c, b]", results.get(1));
		Assert.assertEquals("[b, a, c]", results.get(2));
		Assert.assertEquals("[b, c, a]", results.get(3));
		Assert.assertEquals("[c, b, a]", results.get(4));
		Assert.assertEquals("[c, a, b]", results.get(5));
	}

	@Test
	public void testPermutationFiveIntegers() {
		Integer[] array = { 1, 2, 3, 4, 5 };
		PermutationIterator<Integer> permutator = new PermutationIterator<Integer>(array);

		int counter = 0;
		while (permutator.hasNext()) {
			permutator.next(); // System.out.println(Arrays.toString(permutator.next()));
			counter++;
		}

		Assert.assertEquals(120, counter); // n! = 5! = 120
	}

	@Test
	public void testSubPermutationFiveIntegersSizeThree() {
		Integer[] array = { 1, 2, 3, 4, 5 };
		PermutationIterator<Integer> permutator = new PermutationIterator<Integer>(array, 3);

		int counter = 0;
		while (permutator.hasNext()) {
			permutator.next(); // System.out.println(Arrays.toString(permutator.next()));
			counter++;
		}

		Assert.assertEquals(60, counter); // n!/(n-r)! = 5!/(5-3)! = 60
	}
}
