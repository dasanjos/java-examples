package com.dasanjos.java.util.math;

import java.util.Arrays;
import java.util.Iterator;

/**
 * <p>
 * Permutation (with repetition) enumerator <br/>
 * (where order of elements matter and elements can be repeated)
 * </p>
 * 
 * @author Daniel Arruda
 * 
 * @param <T> type of element array to be permuted
 */

public class PermutationWithRepetitionIterator<T> implements Iterator<T[]> {
	private T[] array;

	private T[] result;

	private int size;

	private int total;

	private int current;

	/**
	 * Constructor to create an permutation iterator of all elements in array parameter
	 * 
	 * @param array elements to be permuted
	 */
	public PermutationWithRepetitionIterator(T[] array) {
		this(array, array.length);
	}

	/**
	 * Constructor that create a sub-permutations iterator (with defined size) of elements in array parameter
	 * 
	 * @param array elements to be permuted
	 * @param size size of sub-permutations
	 */
	public PermutationWithRepetitionIterator(T[] array, int size) {
		this.array = array.clone();
		this.size = size;
		this.total = (int) Math.pow(array.length, size);
		this.current = 0;
	}

	/**
	 * Calculates next permutation available
	 * 
	 * @return next permutation array or null if no more permutations exist
	 */
	@Override
	public T[] next() {
		int[] keys = convertBase(current++, array.length);
		result = Arrays.copyOf(array, size);
		for (int i = 0; i < keys.length; i++) {
			result[i] = array[keys[i]];
		}
		return result;
	}

	@Override
	public boolean hasNext() {
		return (current < total);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Convert a number base 10 to another base
	 * 
	 * @param decimalNumber the input number (base 10)
	 * @param base the desired new number base
	 * @return array of integers representing decimal number on new base
	 */
	private int[] convertBase(int decimalNumber, int base) {
		int[] result = new int[size];
		int i = size;
		while (decimalNumber >= base) {
			result[--i] = (decimalNumber % base);
			decimalNumber = decimalNumber / base;
		}
		result[--i] = decimalNumber;
		return result;
	}
}
