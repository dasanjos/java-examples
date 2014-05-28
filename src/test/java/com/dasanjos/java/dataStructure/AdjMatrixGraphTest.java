package com.dasanjos.java.dataStructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdjMatrixGraphTest {

	AdjMatrixGraph graph;

	@Before
	public void setup() {
		graph = new AdjMatrixGraph(10);
	}

	@Test
	public void basicMethods() {
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(6, 8);
		graph.addEdge(3, 2);

		Assert.assertTrue(graph.hasEdge(6, 8));
		Assert.assertTrue(graph.hasEdge(2, 4));
		Assert.assertTrue(graph.hasEdge(3, 2));
		Assert.assertTrue(graph.hasEdge(1, 3));

		graph.removeEdge(6, 8);
		Assert.assertFalse(graph.hasEdge(6, 8));
	}
}
