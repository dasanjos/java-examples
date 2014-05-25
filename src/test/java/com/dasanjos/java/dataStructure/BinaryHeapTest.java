package com.dasanjos.java.dataStructure;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.dasanjos.java.util.TimeTracker;

public class BinaryHeapTest extends TimeTracker {

	private static final int MAX_SIZE = 100000;
	private Random r = new Random();
	private BinaryHeap bh;

	@Test
	public void insert() {
		insertElements();
	}

	@Test
	public void findMin() {
		insertRandom();
		bh.insert(-1);

		Assert.assertEquals(-1, bh.findMin());
	}

	@Test
	public void deleteLeafNodes() {
		insertElements();

		Assert.assertEquals(10, bh.size());

		Assert.assertEquals(1, bh.deleteMin());
		Assert.assertEquals(9, bh.size());

		Assert.assertEquals(2, bh.deleteMin());
		Assert.assertEquals(8, bh.size());
	}

	private void insertElements() {
		bh = new BinaryHeap(10);
		bh.insert(7);
		bh.insert(5);
		bh.insert(9);
		bh.insert(6);
		bh.insert(4);
		bh.insert(8);
		bh.insert(10);
		bh.insert(1);
		bh.insert(3);
		bh.insert(2);
	}

	private int insertRandom() {
		bh = new BinaryHeap(MAX_SIZE + 1);
		int lastInserted = 0;
		for (int i = 0; i < MAX_SIZE; i++) {
			lastInserted = r.nextInt(MAX_SIZE);
			bh.insert(lastInserted);
		}
		return lastInserted;
	}

}
