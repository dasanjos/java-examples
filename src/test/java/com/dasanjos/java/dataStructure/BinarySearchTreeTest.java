package com.dasanjos.java.dataStructure;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

	private BinarySearchTree bst;

	@Before
	public void setup() {
		bst = new BinarySearchTree();
	}

	@Test
	public void insertRecursive() {
		insertElementsRecursive();
		bst.print();
	}

	@Test
	public void insertIterative() {
		insertElementsIterative();
	}

	@Test
	public void findRecursive() {
		insertElementsRecursive();

		TreeNode result = bst.findRecursive(9);
		Assert.assertNotNull(result);
		Assert.assertEquals("<9>", result.toString());
	}

	@Test
	public void findRecursiveNonExistingElement() {
		insertElementsRecursive();

		Assert.assertNull(bst.findRecursive(11));
	}

	@Test
	public void findIterative() {
		insertElementsIterative();

		TreeNode result = bst.findIterative(9);
		Assert.assertNotNull(result);
		Assert.assertEquals("<9>", result.toString());
	}

	@Test
	public void findIterativeNonExistingElement() {
		insertElementsIterative();

		Assert.assertNull(bst.findIterative(11));
	}

	@Test
	public void traversePreOrder() {
		insertElementsRecursive();

		List<Integer> result = bst.traversePreOrder();
		Assert.assertNotNull(result);
		Assert.assertEquals("[7, 5, 2, 4, 9, 8]", result.toString());
	}

	@Test
	public void traverseInOrder() {
		insertElementsRecursive();

		List<Integer> result = bst.traverseInOrder();
		Assert.assertNotNull(result);
		Assert.assertEquals("[2, 4, 5, 7, 8, 9]", result.toString());
	}

	@Test
	public void traversePostOrder() {
		insertElementsRecursive();

		List<Integer> result = bst.traversePostOrder();
		Assert.assertNotNull(result);
		Assert.assertEquals("[4, 2, 5, 8, 9, 7]", result.toString());
	}

	@Test
	public void traverseBreadthFirst() {
		insertElementsRecursive();

		List<Integer> result = bst.traverseBreadthFirst();
		Assert.assertNotNull(result);
		Assert.assertEquals("[7, 5, 9, 2, 8, 4]", result.toString());
	}

	private void insertElementsRecursive() {
		bst.insertRecursive(7);
		bst.insertRecursive(5);
		bst.insertRecursive(9);
		bst.insertRecursive(2);
		bst.insertRecursive(4);
		bst.insertRecursive(8);
	}

	private void insertElementsIterative() {
		bst.insertIterative(7);
		bst.insertIterative(5);
		bst.insertIterative(9);
		bst.insertIterative(2);
		bst.insertIterative(4);
		bst.insertIterative(8);
	}
}
