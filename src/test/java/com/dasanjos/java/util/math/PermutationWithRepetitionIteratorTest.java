package com.dasanjos.java.util.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dasanjos.java.util.math.PermutationWithRepetitionIterator;

public class PermutationWithRepetitionIteratorTest {

	@Test
	public void testTwoChars() {
		Character[] array = { '0', '1' };
		PermutationWithRepetitionIterator<Character> permutator = new PermutationWithRepetitionIterator<Character>(array);

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
	public void testThreeCharsSizeTwo() {
		Character[] array = { 'a', 'b', 'c' };
		PermutationWithRepetitionIterator<Character> permutator = new PermutationWithRepetitionIterator<Character>(array, 2);

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
	public void testThreeChars() {
		Character[] array = { 'a', 'b', 'c' };
		PermutationWithRepetitionIterator<Character> permutator = new PermutationWithRepetitionIterator<Character>(array);

		List<String> results = new ArrayList<String>();
		while (permutator.hasNext()) {
			results.add(Arrays.toString(permutator.next()));
		}
		
		int i = 0;
		Assert.assertEquals(27, results.size()); // n^r = 3^3 = 27
		Assert.assertEquals("[a, a, a]", results.get(i++));
		Assert.assertEquals("[a, a, b]", results.get(i++));
		Assert.assertEquals("[a, a, c]", results.get(i++));
		Assert.assertEquals("[a, b, a]", results.get(i++));
		Assert.assertEquals("[a, b, b]", results.get(i++));
		Assert.assertEquals("[a, b, c]", results.get(i++));
		Assert.assertEquals("[a, c, a]", results.get(i++));
		Assert.assertEquals("[a, c, b]", results.get(i++));
		Assert.assertEquals("[a, c, c]", results.get(i++));
		Assert.assertEquals("[b, a, a]", results.get(i++));
		Assert.assertEquals("[b, a, b]", results.get(i++));
		Assert.assertEquals("[b, a, c]", results.get(i++));
		Assert.assertEquals("[b, b, a]", results.get(i++));
		Assert.assertEquals("[b, b, b]", results.get(i++));
		Assert.assertEquals("[b, b, c]", results.get(i++));
		Assert.assertEquals("[b, c, a]", results.get(i++));
		Assert.assertEquals("[b, c, b]", results.get(i++));
		Assert.assertEquals("[b, c, c]", results.get(i++));
		Assert.assertEquals("[c, a, a]", results.get(i++));
		Assert.assertEquals("[c, a, b]", results.get(i++));
		Assert.assertEquals("[c, a, c]", results.get(i++));
		Assert.assertEquals("[c, b, a]", results.get(i++));
		Assert.assertEquals("[c, b, b]", results.get(i++));
		Assert.assertEquals("[c, b, c]", results.get(i++));
		Assert.assertEquals("[c, c, a]", results.get(i++));
		Assert.assertEquals("[c, c, b]", results.get(i++));
		Assert.assertEquals("[c, c, c]", results.get(i++));
	}

	@Test
	public void testFiveIntegersSizeThree() {
		Integer[] array = { 1, 2, 3, 4, 5 };
		PermutationWithRepetitionIterator<Integer> permutator = new PermutationWithRepetitionIterator<Integer>(array, 3);

		List<String> results = new ArrayList<String>();
		while (permutator.hasNext()) {
			results.add(Arrays.toString(permutator.next()));
		}

		int i = 0;
		Assert.assertEquals(125, results.size()); // n^r = 5^3 = 125
		Assert.assertEquals("[1, 1, 1]", results.get(i++));
		Assert.assertEquals("[1, 1, 2]", results.get(i++));
		Assert.assertEquals("[1, 1, 3]", results.get(i++));
		Assert.assertEquals("[1, 1, 4]", results.get(i++));
		Assert.assertEquals("[1, 1, 5]", results.get(i++));
		Assert.assertEquals("[1, 2, 1]", results.get(i++));
		Assert.assertEquals("[1, 2, 2]", results.get(i++));
		Assert.assertEquals("[1, 2, 3]", results.get(i++));
		Assert.assertEquals("[1, 2, 4]", results.get(i++));
		Assert.assertEquals("[1, 2, 5]", results.get(i++));
		Assert.assertEquals("[1, 3, 1]", results.get(i++));
		Assert.assertEquals("[1, 3, 2]", results.get(i++));
		Assert.assertEquals("[1, 3, 3]", results.get(i++));
		Assert.assertEquals("[1, 3, 4]", results.get(i++));
		Assert.assertEquals("[1, 3, 5]", results.get(i++));
		Assert.assertEquals("[1, 4, 1]", results.get(i++));
		Assert.assertEquals("[1, 4, 2]", results.get(i++));
		Assert.assertEquals("[1, 4, 3]", results.get(i++));
		Assert.assertEquals("[1, 4, 4]", results.get(i++));
		Assert.assertEquals("[1, 4, 5]", results.get(i++));
		Assert.assertEquals("[1, 5, 1]", results.get(i++));
		Assert.assertEquals("[1, 5, 2]", results.get(i++));
		Assert.assertEquals("[1, 5, 3]", results.get(i++));
		Assert.assertEquals("[1, 5, 4]", results.get(i++));
		Assert.assertEquals("[1, 5, 5]", results.get(i++));
		Assert.assertEquals("[2, 1, 1]", results.get(i++));
		Assert.assertEquals("[2, 1, 2]", results.get(i++));
		Assert.assertEquals("[2, 1, 3]", results.get(i++));
		Assert.assertEquals("[2, 1, 4]", results.get(i++));
		Assert.assertEquals("[2, 1, 5]", results.get(i++));
		Assert.assertEquals("[2, 2, 1]", results.get(i++));
		Assert.assertEquals("[2, 2, 2]", results.get(i++));
		Assert.assertEquals("[2, 2, 3]", results.get(i++));
		Assert.assertEquals("[2, 2, 4]", results.get(i++));
		Assert.assertEquals("[2, 2, 5]", results.get(i++));
		Assert.assertEquals("[2, 3, 1]", results.get(i++));
		Assert.assertEquals("[2, 3, 2]", results.get(i++));
		Assert.assertEquals("[2, 3, 3]", results.get(i++));
		Assert.assertEquals("[2, 3, 4]", results.get(i++));
		Assert.assertEquals("[2, 3, 5]", results.get(i++));
		Assert.assertEquals("[2, 4, 1]", results.get(i++));
		Assert.assertEquals("[2, 4, 2]", results.get(i++));
		Assert.assertEquals("[2, 4, 3]", results.get(i++));
		Assert.assertEquals("[2, 4, 4]", results.get(i++));
		Assert.assertEquals("[2, 4, 5]", results.get(i++));
		Assert.assertEquals("[2, 5, 1]", results.get(i++));
		Assert.assertEquals("[2, 5, 2]", results.get(i++));
		Assert.assertEquals("[2, 5, 3]", results.get(i++));
		Assert.assertEquals("[2, 5, 4]", results.get(i++));
		Assert.assertEquals("[2, 5, 5]", results.get(i++));
		Assert.assertEquals("[3, 1, 1]", results.get(i++));
		Assert.assertEquals("[3, 1, 2]", results.get(i++));
		Assert.assertEquals("[3, 1, 3]", results.get(i++));
		Assert.assertEquals("[3, 1, 4]", results.get(i++));
		Assert.assertEquals("[3, 1, 5]", results.get(i++));
		Assert.assertEquals("[3, 2, 1]", results.get(i++));
		Assert.assertEquals("[3, 2, 2]", results.get(i++));
		Assert.assertEquals("[3, 2, 3]", results.get(i++));
		Assert.assertEquals("[3, 2, 4]", results.get(i++));
		Assert.assertEquals("[3, 2, 5]", results.get(i++));
		Assert.assertEquals("[3, 3, 1]", results.get(i++));
		Assert.assertEquals("[3, 3, 2]", results.get(i++));
		Assert.assertEquals("[3, 3, 3]", results.get(i++));
		Assert.assertEquals("[3, 3, 4]", results.get(i++));
		Assert.assertEquals("[3, 3, 5]", results.get(i++));
		Assert.assertEquals("[3, 4, 1]", results.get(i++));
		Assert.assertEquals("[3, 4, 2]", results.get(i++));
		Assert.assertEquals("[3, 4, 3]", results.get(i++));
		Assert.assertEquals("[3, 4, 4]", results.get(i++));
		Assert.assertEquals("[3, 4, 5]", results.get(i++));
		Assert.assertEquals("[3, 5, 1]", results.get(i++));
		Assert.assertEquals("[3, 5, 2]", results.get(i++));
		Assert.assertEquals("[3, 5, 3]", results.get(i++));
		Assert.assertEquals("[3, 5, 4]", results.get(i++));
		Assert.assertEquals("[3, 5, 5]", results.get(i++));
		Assert.assertEquals("[4, 1, 1]", results.get(i++));
		Assert.assertEquals("[4, 1, 2]", results.get(i++));
		Assert.assertEquals("[4, 1, 3]", results.get(i++));
		Assert.assertEquals("[4, 1, 4]", results.get(i++));
		Assert.assertEquals("[4, 1, 5]", results.get(i++));
		Assert.assertEquals("[4, 2, 1]", results.get(i++));
		Assert.assertEquals("[4, 2, 2]", results.get(i++));
		Assert.assertEquals("[4, 2, 3]", results.get(i++));
		Assert.assertEquals("[4, 2, 4]", results.get(i++));
		Assert.assertEquals("[4, 2, 5]", results.get(i++));
		Assert.assertEquals("[4, 3, 1]", results.get(i++));
		Assert.assertEquals("[4, 3, 2]", results.get(i++));
		Assert.assertEquals("[4, 3, 3]", results.get(i++));
		Assert.assertEquals("[4, 3, 4]", results.get(i++));
		Assert.assertEquals("[4, 3, 5]", results.get(i++));
		Assert.assertEquals("[4, 4, 1]", results.get(i++));
		Assert.assertEquals("[4, 4, 2]", results.get(i++));
		Assert.assertEquals("[4, 4, 3]", results.get(i++));
		Assert.assertEquals("[4, 4, 4]", results.get(i++));
		Assert.assertEquals("[4, 4, 5]", results.get(i++));
		Assert.assertEquals("[4, 5, 1]", results.get(i++));
		Assert.assertEquals("[4, 5, 2]", results.get(i++));
		Assert.assertEquals("[4, 5, 3]", results.get(i++));
		Assert.assertEquals("[4, 5, 4]", results.get(i++));
		Assert.assertEquals("[4, 5, 5]", results.get(i++));
		Assert.assertEquals("[5, 1, 1]", results.get(i++));
		Assert.assertEquals("[5, 1, 2]", results.get(i++));
		Assert.assertEquals("[5, 1, 3]", results.get(i++));
		Assert.assertEquals("[5, 1, 4]", results.get(i++));
		Assert.assertEquals("[5, 1, 5]", results.get(i++));
		Assert.assertEquals("[5, 2, 1]", results.get(i++));
		Assert.assertEquals("[5, 2, 2]", results.get(i++));
		Assert.assertEquals("[5, 2, 3]", results.get(i++));
		Assert.assertEquals("[5, 2, 4]", results.get(i++));
		Assert.assertEquals("[5, 2, 5]", results.get(i++));
		Assert.assertEquals("[5, 3, 1]", results.get(i++));
		Assert.assertEquals("[5, 3, 2]", results.get(i++));
		Assert.assertEquals("[5, 3, 3]", results.get(i++));
		Assert.assertEquals("[5, 3, 4]", results.get(i++));
		Assert.assertEquals("[5, 3, 5]", results.get(i++));
		Assert.assertEquals("[5, 4, 1]", results.get(i++));
		Assert.assertEquals("[5, 4, 2]", results.get(i++));
		Assert.assertEquals("[5, 4, 3]", results.get(i++));
		Assert.assertEquals("[5, 4, 4]", results.get(i++));
		Assert.assertEquals("[5, 4, 5]", results.get(i++));
		Assert.assertEquals("[5, 5, 1]", results.get(i++));
		Assert.assertEquals("[5, 5, 2]", results.get(i++));
		Assert.assertEquals("[5, 5, 3]", results.get(i++));
		Assert.assertEquals("[5, 5, 4]", results.get(i++));
		Assert.assertEquals("[5, 5, 5]", results.get(i++));
	}
}
