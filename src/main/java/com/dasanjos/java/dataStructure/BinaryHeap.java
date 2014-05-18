package com.dasanjos.java.dataStructure;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A binary heap (min-heap) is a complete binary tree with elements from a
 * partially ordered set, such that the element at every node is less than (or
 * equal to) the the element at it's left and right child.<br>
 * This Binary Heap is a min-heap implemented with one-based array (root is
 * element 1; children of element n are elements 2n and 2n+1) for simplicity.<br>
 * Complexity: Space O(n), findMin O(1), insert O(logn), deleteMin O(logn),
 * buildHeap O(n);
 */
public class BinaryHeap {

	private int nodes[];
	private int size;
	private int capacity;

	public BinaryHeap(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		this.nodes = new int[capacity + 1];
	}

	// TODO Implement Build Heap - O(n)
	public BinaryHeap(int[] input, int lenght) {
		throw new NotImplementedException();
	}

	public int size() {
		return size;
	}

	public int findMin() {
		if (size <= 0) {
			throw new RuntimeException("Empty Heap is empty.");
		}
		return nodes[1];
	}

	public void insert(int e) {
		if (size >= capacity) {
			throw new RuntimeException("Heap overflow.");
		}

		size++;
		nodes[size] = e;
		percolateUp();
	}

	public int deleteMin() {
		if (size <= 0) {
			throw new RuntimeException("Empty Heap is empty.");
		}
		int min = findMin();
		nodes[1] = nodes[size];
		size--;
		percolateDown();
		return min;
	}

	private void percolateDown() {
		int index = 1;
		while (true) {
			int child = index * 2;
			if (child > size)
				break;
			if (child + 1 <= size) {
				// if there are two children -> take the smallest or
				// if they are equal take the left one
				child = findMin(child, child + 1);
			}
			if (nodes[index] <= nodes[child])
				break;
			swap(index, child);
			index = child;
		}
	}

	private void percolateUp() {
		int index = size();
		while (index > 1) {
			int parent = index / 2;
			if (nodes[index] >= nodes[parent])
				break;
			swap(index, parent);
			index = parent;
		}
	}

	private void swap(int i, int j) {
		int temp = nodes[i];
		nodes[i] = nodes[j];
		nodes[j] = temp;
	}

	private int findMin(int leftChild, int rightChild) {
		if (nodes[leftChild] <= nodes[rightChild]) {
			return leftChild;
		} else {
			return rightChild;
		}
	}
}