package com.dasanjos.java.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A binary tree is a tree where each node has up to two leaves. A binary search
 * tree is a binary tree where the left child contains nodes with values less
 * than the parent node and where the right child only contains nodes with
 * values greater than or equal to the parent. <br>
 * Complexity: Search/Insert/Delete: average O(logn) worst case O(n), Space O(n)
 */
public class BinarySearchTree {
	BSTNode root;

	public void insertRecursive(int data) {
		if (root == null) {
			root = new BSTNode(data);
		} else {
			insertRecursive(root, data);
		}
	}

	private void insertRecursive(BSTNode node, int data) {
		if (data < node.data) {
			if (node.left == null) {
				node.left = new BSTNode(data);
			} else {
				insertRecursive(node.left, data);
			}
		} else {
			if (node.right == null) {
				node.right = new BSTNode(data);
			} else {
				insertRecursive(node.right, data);
			}
		}
	}

	public void insertIterative(int data) {
		BSTNode newNode = new BSTNode(data);
		if (root == null) {
			root = newNode;
		} else {
			BSTNode focus = root;
			BSTNode parent;
			// TODO remove infinite loop
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

	public BSTNode findRecursive(int data) {
		return findRecursive(root, data);
	}

	private BSTNode findRecursive(BSTNode node, int data) {
		if (node == null || node.data == data) {
			return node;
		} else if (data < node.data) {
			return findRecursive(node.left, data);
		} else {
			return findRecursive(node.right, data);
		}
	}

	public BSTNode findIterative(int data) {
		BSTNode focus = root;
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

	private List<Integer> traversePreOrder(BSTNode node) {
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

	private List<Integer> traverseInOrder(BSTNode node) {
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

	private List<Integer> traversePostOrder(BSTNode node) {
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
		Queue<BSTNode> q = new LinkedList<BSTNode>();
		q.add(root);
		while (!q.isEmpty()) {
			BSTNode node = q.remove();

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

	private Integer process(BSTNode node) {
		return (node != null ? node.data : null);
	}

	public boolean isBinarySearchTree() {
		return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}

	private boolean isBST(BSTNode node, int leftData, int rightData) {
		if (node == null)
			return true;

		if (node.data > leftData || node.data < rightData) {
			return false;
		}

		return (isBST(node.left, node.data, rightData) && isBST(node.right,
				leftData, node.data));
	}

	public void print() {
		root.print("", true);
	}

	public BSTNode findMin() {
		if (root != null) {
			return root.minChild();
		}
		return null;
	}

	public boolean delete(int data) {
		return delete(root, data);
	}

	private boolean delete(BSTNode start, int data) {
		// Search for data and set parent
		BSTNode parent, focus;
		parent = focus = start;
		boolean isLeftNode = false;
		while (focus != null && focus.data != data) {
			if (data < focus.data) {
				parent = focus;
				focus = focus.left;
				isLeftNode = true;
			} else {
				parent = focus;
				focus = focus.right;
				isLeftNode = false;
			}
		}

		// return false if not found
		if (focus == null) {
			return false;
		}

		if (focus.left == null && focus.right == null) {
			// Remove Leaf Node
			if (isLeftNode) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			return true;
		} else if (focus.left == null) {
			// Remove node with one right child
			if (isLeftNode) {
				parent.left = focus.right;
			} else {
				parent.right = focus.right;
			}
			return true;
		} else if (focus.right == null) {
			// Remove node with one left child
			if (isLeftNode) {
				parent.left = focus.left;
			} else {
				parent.right = focus.left;
			}
			return true;
		} else {
			// Remove node with two children:
			// Replace by min right child and remove it recursively
			BSTNode minRightChild = focus.right.minChild();
			focus.data = minRightChild.data;
			delete(focus.right, minRightChild.data);
			return true;
		}
	}
}

// TODO use Generics for data
class BSTNode {
	int data;
	BSTNode left;
	BSTNode right;

	BSTNode(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "<" + data + ">";
	}

	BSTNode minChild() {
		BSTNode focus = this;
		while (focus.left != null) {
			focus = focus.left;
		}
		return focus;
	}

	void print(String prefix, boolean isLast) {
		System.out.println(prefix + (isLast ? "L--" : "|--") + data);
		if (right != null) {
			right.print(prefix + (isLast ? "   " : "|  "), (left == null));
		}
		if (left != null) {
			left.print(prefix + (isLast ? "   " : "|  "), true);
		}
	}
}