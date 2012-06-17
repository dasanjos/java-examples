package com.dasanjos.java.zebraPuzzle.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.util.Property;

public class PuzzleRulePossibilityTest {

	private PuzzleSolution solution;

	@Before
	public void setUp() {
		solution = new PuzzleSolution(5);
		solution.getHouse(0).putProperty("color", "red");
		solution.getHouse(1).putProperty("color", "blue");
		solution.getHouse(2).putProperty("color", "yellow");
		solution.getHouse(3).putProperty("color", "green");
		solution.getHouse(4).putProperty("color", "black");
		solution.getHouse(0).putProperty("drink", "milk");
		solution.getHouse(1).putProperty("drink", "water");
		solution.getHouse(2).putProperty("drink", "tea");
		solution.getHouse(3).putProperty("drink", "coffee");
		solution.getHouse(4).putProperty("drink", "cola");
	}

	@Test
	public void testPossibleEmptySolution() {
		PuzzleRule same = new PuzzleRule("SAME", new Property("color", "red"), new Property("drink", "milk"));
		PuzzleRule left = new PuzzleRule("LEFT", new Property("color", "red"), new Property("drink", "milk"));
		PuzzleRule right = new PuzzleRule("RIGHT", new Property("color", "red"), new Property("drink", "milk"));
		PuzzleRule next = new PuzzleRule("NEXT", new Property("color", "red"), new Property("drink", "milk"));

		PuzzleSolution emptySolution = new PuzzleSolution(5);
		Assert.assertTrue(same.isPossibleSolution(emptySolution));
		Assert.assertTrue(left.isPossibleSolution(emptySolution));
		Assert.assertTrue(right.isPossibleSolution(emptySolution));
		Assert.assertTrue(next.isPossibleSolution(emptySolution));

		emptySolution.getHouse(0).putProperty("color", "red");
		emptySolution.getHouse(1).putProperty("color", "blue");
		emptySolution.getHouse(2).putProperty("color", "yellow");
		emptySolution.getHouse(3).putProperty("color", "green");
		emptySolution.getHouse(4).putProperty("color", "black");
		Assert.assertTrue(same.isPossibleSolution(emptySolution));
		Assert.assertTrue(left.isPossibleSolution(emptySolution));
		Assert.assertTrue(right.isPossibleSolution(emptySolution));
		Assert.assertTrue(next.isPossibleSolution(emptySolution));

		emptySolution.getHouse(0).putProperty("pet", "dog");
		emptySolution.getHouse(1).putProperty("pet", "snail");
		emptySolution.getHouse(2).putProperty("pet", "fox");
		emptySolution.getHouse(3).putProperty("pet", "horse");
		emptySolution.getHouse(4).putProperty("pet", "zebra");
		Assert.assertTrue(same.isPossibleSolution(emptySolution));
		Assert.assertTrue(left.isPossibleSolution(emptySolution));
		Assert.assertTrue(right.isPossibleSolution(emptySolution));
		Assert.assertTrue(next.isPossibleSolution(emptySolution));
	}

	@Test
	public void testPossibleSolution() {
		PuzzleRule same = new PuzzleRule("SAME", new Property("color", "red"), new Property("drink", "milk"));
		Assert.assertTrue(same.isPossibleSolution(solution));

		PuzzleRule left = new PuzzleRule("LEFT", new Property("color", "red"), new Property("drink", "water"));
		Assert.assertTrue(left.isPossibleSolution(solution));
		left = new PuzzleRule("LEFT", new Property("color", "red"), new Property("color", "blue"));
		Assert.assertTrue(left.isPossibleSolution(solution));

		PuzzleRule right = new PuzzleRule("RIGHT", new Property("color", "blue"), new Property("drink", "milk"));
		Assert.assertTrue(right.isPossibleSolution(solution));
		right = new PuzzleRule("RIGHT", new Property("color", "blue"), new Property("color", "red"));
		Assert.assertTrue(right.isPossibleSolution(solution));

		PuzzleRule next = new PuzzleRule("NEXT", new Property("color", "red"), new Property("drink", "water"));
		Assert.assertTrue(next.isPossibleSolution(solution));
		next = new PuzzleRule("NEXT", new Property("color", "blue"), new Property("drink", "milk"));
		Assert.assertTrue(next.isPossibleSolution(solution));
	}

	@Test
	public void testImpossibleSolution() {
		PuzzleRule rule = new PuzzleRule("SAME", new Property("color", "red"), new Property("drink", "water"));
		Assert.assertFalse(rule.isPossibleSolution(solution));

		PuzzleRule left = new PuzzleRule("LEFT", new Property("color", "red"), new Property("drink", "cola"));
		Assert.assertFalse(left.isPossibleSolution(solution));
		left = new PuzzleRule("LEFT", new Property("color", "red"), new Property("drink", "milk"));
		Assert.assertFalse(left.isPossibleSolution(solution));

		PuzzleRule right = new PuzzleRule("RIGHT", new Property("color", "black"), new Property("drink", "milk"));
		Assert.assertFalse(right.isPossibleSolution(solution));
		right = new PuzzleRule("RIGHT", new Property("color", "red"), new Property("drink", "milk"));
		Assert.assertFalse(right.isPossibleSolution(solution));

		PuzzleRule next = new PuzzleRule("NEXT", new Property("color", "red"), new Property("drink", "milk"));
		Assert.assertFalse(next.isPossibleSolution(solution));
		next = new PuzzleRule("NEXT", new Property("color", "black"), new Property("drink", "milk"));
		Assert.assertFalse(next.isPossibleSolution(solution));
	}
}
