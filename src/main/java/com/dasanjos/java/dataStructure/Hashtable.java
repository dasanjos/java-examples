package com.dasanjos.java.dataStructure;

public class Hashtable {

	HashtableNode[] nodes;
	int capacity;
	int size;

	public Hashtable(int capacity) {
		this.capacity = capacity;
		this.nodes = new HashtableNode[capacity];
	}

	public int hash(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash = 31 * hash + key.charAt(i);
		}
		return hash % capacity;
	}

	public void add(String key, String value) {
		nodes[hash(key)] = new HashtableNode(key, value);
		size++;
	}

	public void remove(String key) {
		nodes[hash(key)] = null;
		size--;
	}

	public boolean contains(String key) {
		return (nodes[hash(key)] != null);
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("HashTable:|");
		for (HashtableNode node : nodes) {
			sb.append(node == null ? "-" : node.toString());
			sb.append('|');
		}
		return sb.toString();
	}
}

class HashtableNode {
	private String key;
	private String value;

	public HashtableNode(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		return new StringBuilder("<node key='").append(this.key)
				.append("' value='").append(this.value).append("'/>")
				.toString();
	}
}
