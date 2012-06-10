package com.dasanjos.java;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dasanjos.java.ZebraPuzzle.Solution;
import com.dasanjos.java.util.Property;

/**
 * Unit test for simple Zebra Puzzle.
 */
public class ZebraPuzzleTest {

	@Test
	public void generateSolutionsOneHousesOneProperty() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("nationality", "Norwegian"));

		List<Solution> s = zebraPuzzle.generateSolutions(1, properties);
		assertEquals(1, s.size()); // 1! ^ 1
		assertEquals("[House1 [nationality:Norwegian]]", s.get(0).toString());
	}

	@Test
	public void generateSolutionsTwoHousesTwoProperties() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("nationality", "Norwegian"));// 0
		properties.add(new Property("nationality", "Ukrainian"));// 1
		properties.add(new Property("color", "Red")); // A
		properties.add(new Property("color", "Blue"));// B

		List<Solution> s = zebraPuzzle.generateSolutions(2, properties);
		assertEquals(4, s.size()); // 2 * 2!
		assertEquals("[House1 [color:Blue, nationality:Norwegian], House2 [color:Red, nationality:Ukrainian]]", s.get(0).toString()); // 10-01
		assertEquals("[House1 [color:Blue, nationality:Ukrainian], House2 [color:Red, nationality:Norwegian]]", s.get(1).toString()); // 11-00
		assertEquals("[House1 [color:Red, nationality:Norwegian], House2 [color:Blue, nationality:Ukrainian]]", s.get(2).toString()); // 00-11
		assertEquals("[House1 [color:Red, nationality:Ukrainian], House2 [color:Blue, nationality:Norwegian]]", s.get(3).toString()); // 01-10
	}

	@Test
	public void generateSolutionsThreeHousesThreeProperties() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("nationality", "Norwegian"));
		properties.add(new Property("nationality", "Ukrainian"));
		properties.add(new Property("nationality", "English"));
		properties.add(new Property("color", "Yellow"));
		properties.add(new Property("color", "Blue"));
		properties.add(new Property("color", "Red"));
		properties.add(new Property("drink", "Water"));
		properties.add(new Property("drink", "Tea"));
		properties.add(new Property("drink", "Milk"));

		List<Solution> solutions = zebraPuzzle.generateSolutions(3, properties);
		assertEquals(216, solutions.size()); // 3! ^ 3
	}

	@Test
	public void generateSolutionsThreeHousesThreeProperties0() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("n", "N"));
		properties.add(new Property("n", "U"));
		properties.add(new Property("n", "E"));
		properties.add(new Property("c", "Y"));
		properties.add(new Property("c", "B"));
		properties.add(new Property("c", "R"));
		properties.add(new Property("d", "W"));
		properties.add(new Property("d", "T"));
		properties.add(new Property("d", "M"));

		List<Solution> s = zebraPuzzle.generateSolutions(3, properties);
		assertEquals(216, s.size()); // 3! ^ 3
		int i = 0;
		assertEquals("[House1 [c:B, d:M, n:E], House2 [c:R, d:T, n:N], House3 [c:Y, d:W, n:U]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:E], House2 [c:R, d:T, n:U], House3 [c:Y, d:W, n:N]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:N], House2 [c:R, d:T, n:E], House3 [c:Y, d:W, n:U]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:N], House2 [c:R, d:T, n:U], House3 [c:Y, d:W, n:E]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:U], House2 [c:R, d:T, n:N], House3 [c:Y, d:W, n:E]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:U], House2 [c:R, d:T, n:E], House3 [c:Y, d:W, n:N]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:E], House2 [c:R, d:W, n:N], House3 [c:Y, d:T, n:U]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:E], House2 [c:R, d:W, n:U], House3 [c:Y, d:T, n:N]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:N], House2 [c:R, d:W, n:E], House3 [c:Y, d:T, n:U]]", s.get(i++).toString());
		assertEquals("[House1 [c:B, d:M, n:N], House2 [c:R, d:W, n:U], House3 [c:Y, d:T, n:E]]", s.get(i++).toString());
	}

}
