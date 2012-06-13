package com.dasanjos.java.zebraPuzzle.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.util.Property;

public class PuzzleRuleTest {

	private PuzzleSolution solution;

	@Before
	public void setUp() {
		solution = new PuzzleSolution(3);
		solution.getHouse(0).putProperty("color", "red");
		solution.getHouse(0).putProperty("drink", "milk");

		solution.getHouse(1).putProperty("color", "blue");
		solution.getHouse(1).putProperty("drink", "tea");

		solution.getHouse(2).putProperty("color", "yellow");
		solution.getHouse(2).putProperty("drink", "water");
	}

	@Test
	public void testSameHouseValidSolution() {
		PuzzleRule rule = new PuzzleRule("SAME", new Property("color", "blue"), new Property("drink", "tea"));
		Assert.assertTrue(rule.isValidSolution(solution));
	}

	@Test
	public void testSameHouseNotValidSolution() {
		PuzzleRule rule = new PuzzleRule("SAME", new Property("color", "blue"), new Property("drink", "water"));
		Assert.assertFalse(rule.isValidSolution(solution));
	}

	@Test
	public void testLeftHouseValidSolution() {
		PuzzleRule rule = new PuzzleRule("LEFT", new Property("color", "red"), new Property("color", "blue"));
		Assert.assertTrue(rule.isValidSolution(solution));
	}

	@Test
	public void testLeftHouseNotValidSolution() {
		PuzzleRule rule = new PuzzleRule("LEFT", new Property("color", "red"), new Property("color", "yellow"));
		Assert.assertFalse(rule.isValidSolution(solution));
	}

	@Test
	public void testRightHouseValidSolution() {
		PuzzleRule rule = new PuzzleRule("RIGHT", new Property("color", "blue"), new Property("color", "red"));
		Assert.assertTrue(rule.isValidSolution(solution));
	}

	@Test
	public void testRightHouseNotValidSolution() {
		PuzzleRule rule = new PuzzleRule("RIGHT", new Property("color", "red"), new Property("color", "yellow"));
		Assert.assertFalse(rule.isValidSolution(solution));
	}

	@Test
	public void testNextHouseValidSolution() {
		PuzzleRule rule = new PuzzleRule("NEXT", new Property("color", "blue"), new Property("color", "red"));
		Assert.assertTrue(rule.isValidSolution(solution));

		rule = new PuzzleRule("NEXT", new Property("color", "red"), new Property("color", "blue"));
		Assert.assertTrue(rule.isValidSolution(solution));
	}

	@Test
	public void testNextHouseNotValidSolution() {
		PuzzleRule rule = new PuzzleRule("NEXT", new Property("color", "red"), new Property("color", "yellow"));
		Assert.assertFalse(rule.isValidSolution(solution));
	}
}
