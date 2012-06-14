package com.dasanjos.java.zebraPuzzle;

import static junit.framework.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.dasanjos.java.util.file.CSVReader;
import com.dasanjos.java.zebraPuzzle.model.PuzzleRule;
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
 * For houseNr = 3 -> PermNr =   6 and SolNr =                     216 (hundreds) 
 * For houseNr = 4 -> PermNr =  24 and SolNr =                 331,776 (thousands)
 * For houseNr = 5 -> PermNr = 120 and SolNr =          24,883,200,000 (billions) 
 * For houseNr = 6 -> PermNr = 720 and SolNr = 139,314,069,504,000,000 (quadrillions)
 * 
 * Sources:
 * * http://www.statisticshowto.com/calculators/permutation-calculator-and-combination-calculator/
 * * http://www.calculatorsoup.com/calculators/conversions/numberstowords.php
 * </pre>
 * 
 */
public class BruteForceSolverTest {

	private static final String INPUT1 = "1\nSAME,nationality,Norwegian\n";

	private static final String INPUT2 = "2\nSAME,nationality,Norwegian,color,Red\n" + "SAME,nationality,Ukrainian,color,Blue\n"
			+ "LEFT,color,Red,color,Blue\n";

	private static final String INPUT3 = "3\nSAME,drink,Water,color,Yellow\n" + "SAME,nationality,Ukrainian,color,Blue\n"
			+ "SAME,nationality,English,color,Red\n" + "LEFT,drink,Water,drink,Tea\n" + "LEFT,color,Blue,drink,Milk\n"
			+ "SAME,nationality,Norwegian,position,1\n";

	@Test
	public void generateSolutionsOneHousesOneProperty() throws FileNotFoundException {
		BruteForceSolver puzzle = new BruteForceSolver(new CSVReader(INPUT1, ","));

		assertEquals(1, puzzle.houses);
		assertEquals(1, puzzle.properties.size());
		assertEquals("nationality", puzzle.properties.get(0).getKey());
		assertEquals("Norwegian", puzzle.properties.get(0).getValue());
		assertEquals(0, puzzle.rules.size());

		List<PuzzleSolution> s = puzzle.generateSolutions();
		assertEquals(1, s.size()); // 1! ^ 1
		assertEquals("[{nationality=Norwegian, position=1}]", s.get(0).toString());

		List<PuzzleSolution> result = puzzle.getValidSolutions(s);
		assertEquals(1, result.size());
	}

	@Test
	public void generateSolutionsTwoHousesTwoProperties() {
		BruteForceSolver puzzle = new BruteForceSolver(new CSVReader(INPUT2, ","));

		// TODO Move input test to a separated method
		assertEquals(2, puzzle.houses);
		assertEquals(4, puzzle.properties.size());
		assertEquals("nationality", puzzle.properties.get(0).getKey());
		assertEquals("Norwegian", puzzle.properties.get(0).getValue());
		assertEquals(3, puzzle.rules.size());
		assertEquals(PuzzleRule.Position.SAME, puzzle.rules.get(0).getPosition());
		assertEquals(PuzzleRule.Position.SAME, puzzle.rules.get(1).getPosition());

		List<PuzzleSolution> s = puzzle.generateSolutions();
		assertEquals(4, s.size()); // 2 * 2!
		assertEquals("[{color=Blue, nationality=Norwegian, position=1}, {color=Red, nationality=Ukrainian, position=2}]", s.get(0).toString()); // 10-01
		assertEquals("[{color=Blue, nationality=Ukrainian, position=1}, {color=Red, nationality=Norwegian, position=2}]", s.get(1).toString()); // 11-00
		assertEquals("[{color=Red, nationality=Norwegian, position=1}, {color=Blue, nationality=Ukrainian, position=2}]", s.get(2).toString()); // 00-11
		assertEquals("[{color=Red, nationality=Ukrainian, position=1}, {color=Blue, nationality=Norwegian, position=2}]", s.get(3).toString()); // 01-10

		List<PuzzleSolution> result = puzzle.getValidSolutions(s);
		assertEquals(1, result.size());
	}

	@Test
	public void generateSolutionsThreeHousesThreeProperties() {
		BruteForceSolver puzzle = new BruteForceSolver(new CSVReader(INPUT3, ","));

		List<PuzzleSolution> s = puzzle.generateSolutions();
		assertEquals(216, s.size()); // 3! ^ 3
		assertEquals(
				"[{color=Blue, drink=Milk, nationality=English, position=1}, {color=Red, drink=Tea, nationality=Norwegian, position=2}, {color=Yellow, drink=Water, nationality=Ukrainian, position=3}]",
				s.get(0).toString());
		assertEquals(
				"[{color=Blue, drink=Milk, nationality=English, position=1}, {color=Red, drink=Tea, nationality=Ukrainian, position=2}, {color=Yellow, drink=Water, nationality=Norwegian, position=3}]",
				s.get(1).toString());
		assertEquals(
				"[{color=Blue, drink=Milk, nationality=Norwegian, position=1}, {color=Red, drink=Tea, nationality=English, position=2}, {color=Yellow, drink=Water, nationality=Ukrainian, position=3}]",
				s.get(2).toString());
		assertEquals(
				"[{color=Blue, drink=Milk, nationality=Norwegian, position=1}, {color=Red, drink=Tea, nationality=Ukrainian, position=2}, {color=Yellow, drink=Water, nationality=English, position=3}]",
				s.get(3).toString());

		List<PuzzleSolution> result = puzzle.getValidSolutions(s);
		assertEquals(1, result.size());
	}
}
