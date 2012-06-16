package com.dasanjos.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.dasanjos.java.zebraPuzzle.BruteForceSolver;
import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

/**
 * <p>
 * Java brute-force implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle)
 * </p>
 * 
 * <b>Example input file (input.csv)</b> <br />
 * 
 * <pre>
 * 5
 * SAME,nationality,English,color,Red
 * SAME,nationality,Spaniard,pet,Dog
 * SAME,drink,Coffee,color,Green
 * SAME,drink,Tea,nationality,Ukrainian
 * LEFT,color,Ivory,color,Green
 * SAME,smoke,Old gold,pet,Snails
 * SAME,smoke,Kools,color,Yellow
 * SAME,drink,Milk,position,3
 * SAME,nationality,Norwegian,position,1
 * NEXT,smoke,Chesterfields,pet,Fox
 * NEXT,smoke,Kools,pet,Horse
 * SAME,smoke,Lucky strike,drink,Orange juice
 * SAME,smoke,Parliaments,nationality,Japanese
 * NEXT,color,Blue,nationality,Norwegian
 * SAME,drink,Water
 * SAME,pet,Zebra
 * </pre>
 * 
 * <b>Example output file (output.xml)</b><br />
 * 
 * <pre>
 * &lt;solutions>
 *   &lt;solution>
 *     &lt;house position="1" color="Yellow" nationality="Norwegian" drink="Water"        smoke="Kools"         pet="Fox"/>
 *     &lt;house position="2" color="Blue"   nationality="Ukrainian" drink="Tea"          smoke="Chesterfields" pet="Horse"/>
 *     &lt;house position="3" color="Red"    nationality="English"   drink="Milk"         smoke="Old gold"      pet="Snails"/>
 *     &lt;house position="4" color="Ivory"  nationality="Spaniard"  drink="Orange juice" smoke="Lucky strike"  pet="Dog"/>
 *     &lt;house position="5" color="Green"  nationality="Japanese"  drink="Coffee"       smoke="Parliaments"   pet="Zebra"/>
 *   &lt;/solution>
 * &lt;/solutions>
 * </pre>
 */
public class ZebraPuzzle {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Validate args for paramenters (filepaths) input.csv and output.xml
		File input = new File(args[0]);

		// Parse Input
		BruteForceSolver puzzle = new BruteForceSolver(input);

		// Generate all valid Solutions
		List<PuzzleSolution> solutions = puzzle.generateValidSolutions();

		for (PuzzleSolution solution : solutions) {
			System.out.println(solution);
		}
	}
}
