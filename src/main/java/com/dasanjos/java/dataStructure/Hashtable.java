package com.dasanjos.java.dataStructure;

/**
 * A Hash Table implementation using Open addressing (linear probing) for
 * collision resolution, multiplication by prime number (module hash capacity)
 * as hash function, and has a fixed size (no dynamic resizing support).<br>
 * Complexity:
 */

public class HashTable {

	private Node[] nodes;
	private int capacity;
	private int size;

	public HashTable(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		this.nodes = new Node[capacity + 1];
	}

	public void insert(String key, String value) {
		if (size >= capacity) {
			throw new RuntimeException("Maximum capacity reached");
		}

		int i = hash(key);
		while (!isEmpty(nodes[i])) {
			i++;
		}
		nodes[i] = new Node(key, value);
		size++;
	}

	public Node search(String key) {
		int i = hash(key);

		if (isEmpty(nodes[i])) {
			return null;
		}

		int c = 0;
		while (nodes[i] != null && c < capacity) {
			if (nodes[i].getKey().equals(key)) {
				return nodes[i];
			}
			i++;
			c++;
		}

		return null;
	}

	public void delete(String key) {
		Node n = search(key);
		if (n != null) {
			n.setDeleted(true);
			size--;
		}
	}

	public int size() {
		return size;
	}

	private int hash(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash = 31 * hash + key.charAt(i);
		}
		return hash % capacity;
	}

	private boolean isEmpty(Node node) {
		return node == null || node.isDeleted();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("HashTable:|");
		for (Node node : nodes) {
			sb.append(isEmpty(node) ? "-" : node.toString());
			sb.append('|');
		}
		return sb.toString();
	}
}

class Node {
	private String key;
	private String value;
	private boolean deleted;

	public Node(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean value) {
		this.deleted = value;
	}

	public String getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		return new StringBuilder("k='").append(this.key).append("' v='")
				.append(this.value).toString();
	}
}
