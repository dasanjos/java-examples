package com.dasanjos.java.zebraPuzzle;

import java.util.ArrayList;
import java.util.List;

import com.dasanjos.java.ZebraPuzzle;
import com.dasanjos.java.util.Property;
import com.dasanjos.java.util.math.PermutationIterator;
import com.dasanjos.java.util.math.PermutationWithRepetitionIterator;
import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

/**
 * Java Brute Force implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle) <br />
 * Generate solutions within seconds for puzzles with 1 to 4 houses, but it's impractical (hours) for 5 or more houses.
 */
public class BruteForceAlgorithm extends ZebraPuzzle {

	/**
	 * Generate all possible solutions based on the nr. of houses (rows) and nr. of properties (columns) and filter solutions based on rules
	 * 
	 * @return List of valid solutions
	 */
	public List<PuzzleSolution> generateValidSolutions() {
		// Initialize array of possible permutations indexes
		Integer[] permIndex = new Integer[houses];
		for (int nr = 0; nr < houses; nr++) {
			permIndex[nr] = nr;
		}

		// Generate all permutations without repetition of properties (propNr!)
		PermutationIterator<Integer> propPermutator = new PermutationIterator<Integer>(permIndex);
		List<Integer[]> propPermutations = new ArrayList<Integer[]>();
		while (propPermutator.hasNext()) {
			propPermutations.add(propPermutator.next());
		}

		// Initialize array of possible combinations indexes
		Integer[] solutionIndex = new Integer[propPermutations.size()];
		for (int nr = 0; nr < propPermutations.size(); nr++) {
			solutionIndex[nr] = nr;
		}

		// Generate all permutations with repetition of previous permutations
		PermutationWithRepetitionIterator<Integer> solPermutator = new PermutationWithRepetitionIterator<Integer>(solutionIndex, houses);
		while (solPermutator.hasNext()) {
			// Map this combination of Permutations to a Solution
			Integer[] solPermutation = solPermutator.next();
			PuzzleSolution solution = new PuzzleSolution(houses);
			for (int nr = 0; nr < houses; nr++) {
				Integer[] propPermutation = propPermutations.get(solPermutation[nr]);
				for (int k = 0; k < propPermutation.length; k++) {
					String key = keys[nr];
					solution.getHouse(k).putProperty(key, Property.getValues(key, properties).get(propPermutation[k]));
				}
			}
			if (solution.isValid(rules)) {
				solutions.add(solution);
			}
		}
		return solutions;
	}
}
