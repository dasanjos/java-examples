package com.dasanjos.java.zebraPuzzle;

import static junit.framework.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

/**
 * Unit tests for Zebra Puzzle. <br/>
 * Assuming that Number of Houses is equal to Number of Keys and Values for each Key, this formula describe the number of possible solutions:
 * 
 * <pre>
 * Number of Solutions is the Number of combinations with size [number of houses] of properties permutations
 * Number of properties permutations => PermNr = HouseNr! 
 * Number of Solutions = SolNr = PermNr! ^ HouseNr!
 * 
 * For houseNr = 2 -> PermNr =   2 and SolNr =                       4
 * For houseNr = 3 -> PermNr =   6 and SolNr =                     216 (hundreds - solved in milliseconds) 
 * For houseNr = 4 -> PermNr =  24 and SolNr =                 331,776 (thousands - solved in seconds)		
 * For houseNr = 5 -> PermNr = 120 and SolNr =          24,883,200,000 (billions - solved in days) 
 * For houseNr = 6 -> PermNr = 720 and SolNr = 139,314,069,504,000,000 (quadrillions - too large to be solved)
 * 
 * Sources:
 * * http://www.statisticshowto.com/calculators/permutation-calculator-and-combination-calculator/
 * * http://www.calculatorsoup.com/calculators/conversions/numberstowords.php
 * </pre>
 * 
 */
public class BruteForceAlgorithmTest {

	@Test
	public void generateSolutionsOneHouse() throws FileNotFoundException {
		GenericBruteForceAlgorithm puzzle = new GenericBruteForceAlgorithm();
		puzzle.parseInputCSV("src/test/resources/input1.csv");
		List<PuzzleSolution> solutions = puzzle.generateValidSolutions();
		assertEquals(1, solutions.size());
		assertEquals("[{nationality=Norwegian, position=1}]", solutions.get(0).toString());
	}

	@Test
	public void generateSolutionsTwoHouses() throws FileNotFoundException {
		GenericBruteForceAlgorithm puzzle = new GenericBruteForceAlgorithm();
		puzzle.parseInputCSV("src/test/resources/input2.csv");
		List<PuzzleSolution> solutions = puzzle.generateValidSolutions();
		assertEquals(1, solutions.size());
		assertEquals("[{color=Red, nationality=Norwegian, position=1}, {color=Blue, nationality=Ukrainian, position=2}]", solutions.get(0).toString());
	}

	@Test
	public void generateSolutionsThreeHouses() throws FileNotFoundException {
		GenericBruteForceAlgorithm puzzle = new GenericBruteForceAlgorithm();
		puzzle.parseInputCSV("src/test/resources/input3.csv");
		List<PuzzleSolution> solutions = puzzle.generateValidSolutions();
		assertEquals(1, solutions.size());
		assertEquals("[{color=Yellow, drink=Water, nationality=Norwegian, position=1}, {color=Blue, drink=Tea, nationality=Ukrainian, position=2}, "
				+ "{color=Red, drink=Milk, nationality=English, position=3}]", solutions.get(0).toString());
	}

	@Test
	public void generateSolutionsFourHouses() throws FileNotFoundException {
		GenericBruteForceAlgorithm puzzle = new GenericBruteForceAlgorithm();
		puzzle.parseInputCSV("src/test/resources/input4.csv");
		List<PuzzleSolution> solutions = puzzle.generateValidSolutions();
		assertEquals(1, solutions.size());
		assertEquals("[{color=Yellow, drink=Water, nationality=Norwegian, position=1, smoke=Kools},"
				+ " {color=Blue, drink=Tea, nationality=Ukrainian, position=2, smoke=Chesterfields},"
				+ " {color=Red, drink=Milk, nationality=English, position=3, smoke=Old gold},"
				+ " {color=Ivory, drink=Orange juice, nationality=Spaniard, position=4, smoke=Lucky strike}]", solutions.get(0).toString());
	}
}
