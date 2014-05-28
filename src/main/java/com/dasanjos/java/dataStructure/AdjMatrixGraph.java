package com.dasanjos.java.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Implementation of a directed unweighted Graph using Adjacency Matrix
 * 
 * Complexity: addEdge, removeEdge, hasEdge O(1) / outEdges, inEdges O(n) /
 * Space O(n^2)
 */
public class AdjMatrixGraph {
	int size;
	private boolean[][] matrix;
	public static final byte WHITE = 1;
	public static final byte GREY = 2;

	public AdjMatrixGraph(int size) {
		this.size = size;
		this.matrix = new boolean[size][size];
	}

	public void addEdge(int nodeA, int nodeB) {
		matrix[nodeA][nodeB] = true;
	}

	public void removeEdge(int nodeA, int nodeB) {
		matrix[nodeA][nodeB] = false;
	}

	public boolean hasEdge(int nodeA, int nodeB) {
		return matrix[nodeA][nodeB];
	}

	public List<Integer> outEdges(int i) {
		List<Integer> edges = new ArrayList<Integer>();
		for (int j = 0; j < size; j++) {
			if (matrix[i][j]) {
				edges.add(j);
			}
		}
		return edges;
	}

	public List<Integer> inEdges(int i) {
		List<Integer> edges = new ArrayList<Integer>();
		for (int j = 0; j < size; j++) {
			if (matrix[j][i]) {
				edges.add(j);
			}
		}
		return edges;
	}

	public int nVertices() {
		return size;
	}

	public void breadthFirstSearch(int root) {
		boolean[] seen = new boolean[nVertices()];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(root);
		seen[root] = true;
		while (!q.isEmpty()) {
			int i = q.remove();
			for (Integer j : outEdges(i)) {
				if (!seen[j]) {
					q.add(j);
					seen[j] = true;
				}
			}
		}
	}

	public void depthFirstSearch(int r) {
		byte[] c = new byte[nVertices()];
		Stack<Integer> s = new Stack<Integer>();
		s.push(r);
		while (!s.isEmpty()) {
			int i = s.pop();
			if (c[i] == WHITE) {
				c[i] = GREY;
				for (int j : outEdges(i))
					s.push(j);
			}
		}
	}
}
