package com.dasanjos.java.dataStructure;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.util.TimeTracker;

public class BinarySearchTreeTest extends TimeTracker {

	private static final int MAX_SIZE = 100000;
	private Random r = new Random();
	private BinarySearchTree bst;

	@Before
	public void setup() {
		bst = new BinarySearchTree();
	}

	@Test
	public void insertRecursive() {
		insertRandomRecursive();
		Assert.assertTrue(bst.isBinarySearchTree());
	}

	@Test
	public void insertIterative() {
		insertRandomIterative();
		Assert.assertTrue(bst.isBinarySearchTree());
	}

	@Test
	public void findRecursive() {
		int last = insertRandomIterative();

		BSTNode result = bst.findRecursive(last);
		Assert.assertNotNull(result);
		Assert.assertEquals("<" + last + ">", result.toString());
	}

	@Test
	public void findRecursiveNonExistingElement() {
		insertRandomIterative();

		Assert.assertNull(bst.findRecursive(-1));
	}

	@Test
	public void findIterative() {
		int last = insertRandomIterative();

		BSTNode result = bst.findIterative(last);
		Assert.assertNotNull(result);
		Assert.assertEquals("<" + last + ">", result.toString());
	}

	@Test
	public void findIterativeNonExistingElement() {
		insertRandomIterative();

		Assert.assertNull(bst.findRecursive(-1));
	}

	@Test
	public void deleteNode() {
		Assert.fail("not implemented");
	}

	@Test
	public void traversePreOrder() {
		insertElementsIterative();

		List<Integer> result = bst.traversePreOrder();
		Assert.assertNotNull(result);
		Assert.assertEquals("[7, 5, 4, 6, 9, 8, 10]", result.toString());
	}

	@Test
	public void traverseInOrder() {
		insertElementsIterative();

		List<Integer> result = bst.traverseInOrder();
		Assert.assertNotNull(result);
		Assert.assertEquals("[4, 5, 6, 7, 8, 9, 10]", result.toString());
	}

	@Test
	public void traversePostOrder() {
		insertElementsRecursive();

		List<Integer> result = bst.traversePostOrder();
		Assert.assertNotNull(result);
		Assert.assertEquals("[4, 6, 5, 8, 10, 9, 7]", result.toString());
	}

	@Test
	public void traverseBreadthFirst() {
		insertElementsRecursive();

		List<Integer> result = bst.traverseBreadthFirst();
		Assert.assertNotNull(result);
		Assert.assertEquals("[7, 5, 9, 4, 6, 8, 10]", result.toString());
	}

	private void insertElementsRecursive() {
		bst.insertRecursive(7);
		bst.insertRecursive(5);
		bst.insertRecursive(9);
		bst.insertRecursive(6);
		bst.insertRecursive(4);
		bst.insertRecursive(8);
		bst.insertRecursive(10);

	}

	private int insertRandomRecursive() {
		int lastInserted = 0;
		for (int i = 0; i < MAX_SIZE; i++) {
			lastInserted = r.nextInt(MAX_SIZE);
			bst.insertRecursive(lastInserted);
		}
		return lastInserted;
	}

	private void insertElementsIterative() {
		bst.insertIterative(7);
		bst.insertIterative(5);
		bst.insertIterative(9);
		bst.insertIterative(6);
		bst.insertIterative(4);
		bst.insertIterative(8);
		bst.insertIterative(10);
	}

	private int insertRandomIterative() {
		int lastInserted = 0;
		for (int i = 0; i < MAX_SIZE; i++) {
			lastInserted = r.nextInt(MAX_SIZE);
			bst.insertIterative(lastInserted);
		}
		return lastInserted;
	}

}
