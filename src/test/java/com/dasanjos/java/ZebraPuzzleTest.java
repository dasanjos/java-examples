package com.dasanjos.java;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.dasanjos.java.ZebraPuzzle.Solution;
import com.dasanjos.java.util.Property;

/**
 * Unit test for simple Zebra Puzzle.
 */
public class ZebraPuzzleTest {

	@Ignore
	public void generateSolutionsTwoHousesOneProperty() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("nationality", "Norwegian"));
		properties.add(new Property("nationality", "Ukrainian"));

		List<Solution> s = zebraPuzzle.generateSolutions(2, properties);
		Assert.assertEquals(2, s.size()); // 2 * 1!
		Assert.assertEquals("[Position:1 [nationality:Norwegian], Position:2 [nationality:Ukrainian]]", s.get(0).toString());
		Assert.assertEquals("[Position:1 [nationality:Ukrainian], Position:2 [nationality:Norwegian]]", s.get(1).toString());
	}

	@Test
	public void generateSolutionsTwoHousesTwoProperties() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("nationality", "Norwegian"));// 0
		properties.add(new Property("nationality", "Ukrainian"));// 1
		properties.add(new Property("color", "Red")); // 0
		properties.add(new Property("color", "Blue"));// 1

		List<Solution> s = zebraPuzzle.generateSolutions(2, properties);
		Assert.assertEquals(2, s.size()); // 2 * 2!
		Assert.assertEquals("[Position:1 [color:Blue, nationality:Ukrainian], Position:2 [color:Red, nationality:Norwegian]]", s.get(0).toString()); // 11-00
		Assert.assertEquals("[Position:1 [color:Red, nationality:Norwegian], Position:2 [color:Blue, nationality:Ukrainian]]", s.get(1).toString()); // 00-11
		Assert.assertEquals("[Position:1 [color:Blue, nationality:Norwegian], Position:2 [color:Red, nationality:Ukrainian]]", s.get(2).toString()); // 10-01
		Assert.assertEquals("[Position:1 [color:Red, nationality:Ukrainian], Position:2 [color:Blue, nationality:Norwegian]]", s.get(3).toString()); // 01-10
	}

	@Ignore
	public void generateSolutionsTwoHousesThreeProperties() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("nationality", "Norwegian"));
		properties.add(new Property("nationality", "Ukrainian"));
		properties.add(new Property("color", "Yellow"));
		properties.add(new Property("color", "Blue"));
		properties.add(new Property("drink", "Water"));
		properties.add(new Property("drink", "Tea"));

		List<Solution> solutions = zebraPuzzle.generateSolutions(2, properties);
		Assert.assertEquals(12, solutions.size()); // 2 * 3!
	}

	@Ignore
	public void generateSolutionsTwoHousesFourProperties() {
		ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property("nationality", "Norwegian"));
		properties.add(new Property("nationality", "Ukrainian"));
		properties.add(new Property("color", "Yellow"));
		properties.add(new Property("color", "Blue"));
		properties.add(new Property("drink", "Water"));
		properties.add(new Property("drink", "Tea"));
		properties.add(new Property("smoke", "Kools"));
		properties.add(new Property("smoke", "Chesterfields"));

		List<Solution> solutions = zebraPuzzle.generateSolutions(2, properties);
		Assert.assertEquals(48, solutions.size());// 2 * 4!
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
		Assert.assertEquals(120, solutions.size()); // 3 * 3!
	}
}
