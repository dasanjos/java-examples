package com.dasanjos.java.zebraPuzzle;

import static junit.framework.Assert.assertEquals;

import java.io.File;
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
public class BruteForceSolverTest {

	@Test
	public void generateSolutionsOneHousesOneProperty() {
		String input = "1\nSAME,nationality,Norwegian\n";
		BruteForceSolver puzzle = new BruteForceSolver(new CSVReader(input, ","));

		assertEquals(1, puzzle.houses);
		assertEquals(1, puzzle.properties.size());
		assertEquals("nationality", puzzle.properties.get(0).getKey());
		assertEquals("Norwegian", puzzle.properties.get(0).getValue());
		assertEquals(0, puzzle.rules.size());

		List<PuzzleSolution> s = puzzle.generateValidSolutions();
		assertEquals(1, s.size()); // 1! ^ 1
		assertEquals("[{nationality=Norwegian, position=1}]", s.get(0).toString());
	}

	@Test
	public void generateSolutionsTwoHousesTwoProperties() throws FileNotFoundException {
		BruteForceSolver puzzle = new BruteForceSolver(new CSVReader(new File("src/test/resources/input2.csv"), ","));

		assertEquals(2, puzzle.houses);
		assertEquals(4, puzzle.properties.size());
		assertEquals("nationality", puzzle.properties.get(0).getKey());
		assertEquals("Norwegian", puzzle.properties.get(0).getValue());
		assertEquals(3, puzzle.rules.size());
		assertEquals(PuzzleRule.Position.SAME, puzzle.rules.get(0).getPosition());
		assertEquals(PuzzleRule.Position.SAME, puzzle.rules.get(1).getPosition());

		List<PuzzleSolution> s = puzzle.generateValidSolutions();
		assertEquals(1, s.size()); // 2! ^ 2
		assertEquals("[{color=Red, nationality=Norwegian, position=1}, {color=Blue, nationality=Ukrainian, position=2}]", s.get(0).toString());
	}

	@Test
	public void generateSolutionsThreeHousesThreeProperties() throws FileNotFoundException {
		BruteForceSolver puzzle = new BruteForceSolver(new CSVReader(new File("src/test/resources/input3.csv"), ","));

		String result = "[{color=Yellow, drink=Water, nationality=Norwegian, position=1},"
				+ " {color=Blue, drink=Tea, nationality=Ukrainian, position=2}, " + "{color=Red, drink=Milk, nationality=English, position=3}]";
		List<PuzzleSolution> s = puzzle.generateValidSolutions();
		assertEquals(1, s.size()); // 3! ^ 3
		assertEquals(result, s.get(0).toString());
	}
}
