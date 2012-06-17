package com.dasanjos.java;

import java.util.Iterator;

/**
 * Implement a class that takes an iterator in its constructor and implements a peek() method that returns what the following call to next will be.
 * For instance, if the class is passed an iterator to: [1,2,3] 
 * Then these calls will return: peek() -> 1 peek() -> 1 peek() -> 1 next() -> 1 next() -> 2 peek() -> 3 hasNext() -> true next() -> 3 hasNext() -> false
 * 
 */
public class LookAheadIterator {
	
	Integer peek = null;

	Boolean hasNext = null;

	Iterator<Integer> iterator;

	public LookAheadIterator(Iterator<Integer> iterator) {
		this.iterator = iterator;
	}

	public int peek() throws Exception {
		if (peek != null) {
			return peek;
		} else {
			if (iterator.hasNext()) {
				next();
				return peek;
			} else {
				throw new Exception();
			}
		}
	}

	public int next() throws Exception {
		Integer next = peek;
		if (iterator.hasNext()) {
			peek = iterator.next();
			hasNext = true;
		} else {
			peek = null;
			hasNext = false;
		}
		if (next == null) {
			next = peek;
		}
		return next;
	}

	public boolean hasNext() throws Exception {
		if (hasNext != null) {
			return hasNext;
		} else {
			if (iterator.hasNext()) {
				next();
				return hasNext;
			} else {
				throw new Exception();
			}
		}
	}
}
