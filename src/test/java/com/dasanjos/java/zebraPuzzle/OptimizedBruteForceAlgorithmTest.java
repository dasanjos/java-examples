package com.dasanjos.java.zebraPuzzle;

import static junit.framework.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

public class OptimizedBruteForceAlgorithmTest {

	@Test
	public void generateSolutionsFiveHouses() throws FileNotFoundException {
		OptimizedFiveHousesAlgorithm puzzle = new OptimizedFiveHousesAlgorithm();
		puzzle.parseInputCSV("src/test/resources/input5.csv");

		List<PuzzleSolution> solutions = puzzle.generateValidSolutions();
		assertEquals(1, solutions.size());
		assertEquals("[{color=Yellow, drink=Water, nationality=Norwegian, pet=Fox, position=1, smoke=Kools},"
				+ " {color=Blue, drink=Tea, nationality=Ukrainian, pet=Horse, position=2, smoke=Chesterfields},"
				+ " {color=Red, drink=Milk, nationality=English, pet=Snails, position=3, smoke=Old gold},"
				+ " {color=Ivory, drink=Orange juice, nationality=Spaniard, pet=Dog, position=4, smoke=Lucky strike},"
				+ " {color=Green, drink=Coffee, nationality=Japanese, pet=Zebra, position=5, smoke=Parliaments}]", solutions.get(0).toString());
	}
}
