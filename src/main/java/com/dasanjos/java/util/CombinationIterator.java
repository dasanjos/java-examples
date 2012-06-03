package com.dasanjos.java.util;

import java.util.Arrays;
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
		//TODO
	}

	/**
	 * Calculates next combination available
	 * 
	 * @return next combination array or null if no more combinations exist
	 */
	@Override
	public T[] next() {
		//TODO
		return null;
	}

	@Override
	public boolean hasNext() {
		//TODO
		return false;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
