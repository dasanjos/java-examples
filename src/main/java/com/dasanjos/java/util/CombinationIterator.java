package com.dasanjos.java.util;

import java.util.Iterator;

/**
 * <p>
 * Generic Combination enumerator (where order of elements matter and elements can be repeated)
 * </p>
 * 
 * @author Daniel Arruda
 * 
 * @param <T> type of element array to be combined
 */

public class CombinationIterator<T> implements Iterator<T[]> {
	private T[] array;

	private T[] result;

	private int size;

	private int total;

	private int current;

	/**
	 * Constructor to create an combination Iterator of all elements in array parameter
	 * 
	 * @param array elements to be combined
	 */
	public CombinationIterator(T[] array) {
		this(array, array.length);
	}

	/**
	 * Constructor that create a sub-combinations Iterator (with defined size) of elements in array parameter
	 * 
	 * @param array elements to be combined
	 * @param size size of sub-combinations
	 */
	public CombinationIterator(T[] array, int size) {
		this.array = array.clone();
		this.size = size;
		this.total = (int) Math.pow(array.length, size);
		this.current = 0;
	}

	/**
	 * Calculates next combination available
	 * 
	 * @return next combination array or null if no more combinations exist
	 */
	@Override
	public T[] next() {
		int[] keys = convertBase(current++, array.length);
		result = (T[]) new Object[size];
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
