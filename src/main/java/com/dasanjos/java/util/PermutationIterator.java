package com.dasanjos.java.util;

import java.util.Arrays;
import java.util.Iterator;

/**
 * <p>
 * <a href="http://stackoverflow.com/questions/2799078/permutation-algorithm-without-recursion-java">Permutation (without repetition) enumerator</a> <br/>
 * (where order of elements matter and elements can not be repeated)
 * </p>
 * 
 * <pre>
 * The idea behind my algorithm is that any permutation can be expressed as a unique sequence of swap commands. 
 * For example, for [A,B,C], the swap sequence 012 leaves all items in place, while 122 starts by swapping index 0 with index 1, then swaps 1 with 2, and then swaps 2 with 2 (i.e. leaves it in place). 
 * This results in the permutation BCA. 
 * This representation is isomorphic to the permutation representation (i.e. one to one relationship), and it is very easy to "increment" it when traversing the permutations space. 
 * For 4 items, it starts from 0123 (ABCD) and ends with 3333(DABC).
 * </pre>
 * 
 * @author Eyal Schneider
 * 
 * @param <T> type of element array to be permuted
 */

public class PermutationIterator<T> implements Iterator<T[]> {
	private T[] array;

	private int[] swaps;

	/**
	 * Constructor to create an Permutation Iterator of all elements in array parameter
	 * 
	 * @param array elements to be permuted
	 */
	public PermutationIterator(T[] array) {
		this(array, array.length);
	}

	/**
	 * Constructor that create a sub-Permutations Iterator (with defined size) of elements in array parameter
	 * 
	 * @param array elements to be permuted
	 * @param size size of sub-permutations
	 */
	public PermutationIterator(T[] array, int size) {
		this.array = array.clone();
		this.swaps = new int[size];
		for (int i = 0; i < swaps.length; i++)
			swaps[i] = i;
	}

	/**
	 * Calculates next permutation available
	 * 
	 * @return next permutation array or null if no more permutations exist
	 */
	@Override
	public T[] next() {
		if (array == null)
			return null;

		T[] res = Arrays.copyOf(array, swaps.length);
		// Prepare next
		int i = swaps.length - 1;
		while (i >= 0 && swaps[i] == array.length - 1) {
			swap(i, swaps[i]); // Undo the swap
			swaps[i] = i;
			i--;
		}

		if (i < 0)
			array = null;
		else {
			int prev = swaps[i];
			swap(i, prev);
			int next = prev + 1;
			swaps[i] = next;
			swap(i, next);
		}

		return res;
	}

	private void swap(int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	@Override
	public boolean hasNext() {
		if (array == null)
			return false;
		return true;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
