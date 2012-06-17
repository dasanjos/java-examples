package com.dasanjos.java.zebraPuzzle;

import static junit.framework.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.dasanjos.java.util.file.CSVReader;

public class OptimizedBruteForceAlgorithmTest {

	@Test
	public void generateSolutionsFiveHouses() throws FileNotFoundException {
		OptimizedBruteForceAlgorithm puzzle = new OptimizedBruteForceAlgorithm(new CSVReader("src/test/resources/input5.csv"));

		assertEquals(5, puzzle.houses);
		assertEquals(25, puzzle.properties.size());
		assertEquals(14, puzzle.rules.size());

		puzzle.generateValidSolutions();
		assertEquals(1, puzzle.solutions.size());
		assertEquals("[{color=Yellow, drink=Water, nationality=Norwegian, pet=Fox, position=1, smoke=Kools},"
				+ " {color=Blue, drink=Tea, nationality=Ukrainian, pet=Horse, position=2, smoke=Chesterfields},"
				+ " {color=Red, drink=Milk, nationality=English, pet=Snails, position=3, smoke=Old gold},"
				+ " {color=Ivory, drink=Orange juice, nationality=Spaniard, pet=Dog, position=4, smoke=Lucky strike},"
				+ " {color=Green, drink=Coffee, nationality=Japanese, pet=Zebra, position=5, smoke=Parliaments}]", puzzle.solutions.get(0).toString());
	}

}
