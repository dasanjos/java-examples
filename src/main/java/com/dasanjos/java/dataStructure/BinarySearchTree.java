package com.dasanjos.java.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A binary tree is a tree where each node has up to two leaves. A binary search
 * tree is a binary tree where the left child contains nodes with values less
 * than the parent node and where the right child only contains nodes with
 * values greater than or equal to the parent.
 * 
 */
public class BinarySearchTree {
	TreeNode root;

	public void insertRecursive(int data) {
		if (root == null) {
			root = new TreeNode(data);
		} else {
			insertRecursive(root, data);
		}
	}

	private void insertRecursive(TreeNode node, int data) {
		if (data < node.data) {
			if (node.left == null) {
				node.left = new TreeNode(data);
			} else {
				insertRecursive(node.left, data);
			}
		} else {
			if (node.right == null) {
				node.right = new TreeNode(data);
			} else {
				insertRecursive(node.right, data);
			}
		}
	}

	public void insertIterative(int data) {
		TreeNode newNode = new TreeNode(data);
		if (root == null) {
			root = newNode;
		} else {
			TreeNode focus = root;
			TreeNode parent;
			// remove infinite loop
			while (true) {
				parent = focus;
				if (data < focus.data) {
					focus = focus.left;
					if (focus == null) {
						parent.left = newNode;
						return;
					}
				} else {
					focus = focus.right;
					if (focus == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}

	public TreeNode findRecursive(int data) {
		return findRecursive(root, data);
	}

	private TreeNode findRecursive(TreeNode node, int data) {
		if (node == null || node.data == data) {
			return node;
		} else if (data < node.data) {
			return findRecursive(node.left, data);
		} else {
			return findRecursive(node.right, data);
		}
	}

	public TreeNode findIterative(int data) {
		TreeNode focus = root;
		while (focus != null) {
			if (focus.data == data) {
				return focus;
			} else if (data < focus.data) {
				focus = focus.left;
			} else {
				focus = focus.right;
			}
		}
		return null;
	}

	public List<Integer> traversePreOrder() {
		return traversePreOrder(root);
	}

	private List<Integer> traversePreOrder(TreeNode node) {
		List<Integer> result = new ArrayList<Integer>();
		if (node != null) {
			result.add(process(node));
			result.addAll(traversePreOrder(node.left));
			result.addAll(traversePreOrder(node.right));
		}
		return result;
	}

	public List<Integer> traverseInOrder() {
		return traverseInOrder(root);
	}

	private List<Integer> traverseInOrder(TreeNode node) {
		List<Integer> result = new ArrayList<Integer>();
		if (node != null) {
			result.addAll(traverseInOrder(node.left));
			result.add(process(node));
			result.addAll(traverseInOrder(node.right));
		}
		return result;
	}

	public List<Integer> traversePostOrder() {
		return traversePostOrder(root);
	}

	private List<Integer> traversePostOrder(TreeNode node) {
		List<Integer> result = new ArrayList<Integer>();
		if (node != null) {
			result.addAll(traversePostOrder(node.left));
			result.addAll(traversePostOrder(node.right));
			result.add(process(node));
		}
		return result;
	}

	public List<Integer> traverseBreadthFirst() {
		List<Integer> result = new ArrayList<Integer>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.remove();

			result.add(process(node));

			if (node.left != null) {
				q.add(node.left);
			}
			if (node.right != null) {
				q.add(node.right);
			}
		}
		return result;
	}

	private Integer process(TreeNode node) {
		return (node != null ? node.data : null);
	}

	public boolean isBinarySearchTree() {
		return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}

	private boolean isBST(TreeNode node, int leftData, int rightData) {
		if (node == null)
			return true;

		if (node.data > leftData || node.data <= rightData) {
			return false;
		}

		return (isBST(node.left, node.data, rightData) && isBST(node.right,
				leftData, node.data));
	}

	public void print() {
		root.print("", true);
	}
}

// TODO use generics for data
class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;

	TreeNode(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "<" + String.valueOf(data) + ">";
	}

	void print(String prefix, boolean isLast) {
		System.out.println(prefix + (isLast ? "L--" : "|--") + data);
		if (left != null) {
			left.print(prefix + (isLast ? "   " : "|  "), (right == null));
		}
		if (right != null) {
			right.print(prefix + (isLast ? "   " : "|  "), true);
		}
	}
}