package com.dasanjos.java.zebraPuzzle;

import java.util.List;

import com.dasanjos.java.ZebraPuzzle;
import com.dasanjos.java.util.Property;
import com.dasanjos.java.zebraPuzzle.model.PuzzleRule;
import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

/**
 * Optimized (A*) implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle) <br />
 * Hard coded optimizations for 5 houses, cut impossible solutions from inception (based on rules), evaluating only possible combinations.
 */
public class OptimizedFiveHousesAlgorithm extends ZebraPuzzle {

	int permutations[][] = { { 4, 3, 2, 1, 0 }, { 3, 4, 2, 1, 0 }, { 4, 2, 3, 1, 0 }, { 2, 4, 3, 1, 0 }, { 3, 2, 4, 1, 0 }, { 2, 3, 4, 1, 0 },
			{ 4, 3, 1, 2, 0 }, { 3, 4, 1, 2, 0 }, { 4, 1, 3, 2, 0 }, { 1, 4, 3, 2, 0 }, { 3, 1, 4, 2, 0 }, { 1, 3, 4, 2, 0 }, { 4, 2, 1, 3, 0 },
			{ 2, 4, 1, 3, 0 }, { 4, 1, 2, 3, 0 }, { 1, 4, 2, 3, 0 }, { 2, 1, 4, 3, 0 }, { 1, 2, 4, 3, 0 }, { 3, 2, 1, 4, 0 }, { 2, 3, 1, 4, 0 },
			{ 3, 1, 2, 4, 0 }, { 1, 3, 2, 4, 0 }, { 2, 1, 3, 4, 0 }, { 1, 2, 3, 4, 0 }, { 4, 3, 2, 0, 1 }, { 3, 4, 2, 0, 1 }, { 4, 2, 3, 0, 1 },
			{ 2, 4, 3, 0, 1 }, { 3, 2, 4, 0, 1 }, { 2, 3, 4, 0, 1 }, { 4, 3, 0, 2, 1 }, { 3, 4, 0, 2, 1 }, { 4, 0, 3, 2, 1 }, { 0, 4, 3, 2, 1 },
			{ 3, 0, 4, 2, 1 }, { 0, 3, 4, 2, 1 }, { 4, 2, 0, 3, 1 }, { 2, 4, 0, 3, 1 }, { 4, 0, 2, 3, 1 }, { 0, 4, 2, 3, 1 }, { 2, 0, 4, 3, 1 },
			{ 0, 2, 4, 3, 1 }, { 3, 2, 0, 4, 1 }, { 2, 3, 0, 4, 1 }, { 3, 0, 2, 4, 1 }, { 0, 3, 2, 4, 1 }, { 2, 0, 3, 4, 1 }, { 0, 2, 3, 4, 1 },
			{ 4, 3, 1, 0, 2 }, { 3, 4, 1, 0, 2 }, { 4, 1, 3, 0, 2 }, { 1, 4, 3, 0, 2 }, { 3, 1, 4, 0, 2 }, { 1, 3, 4, 0, 2 }, { 4, 3, 0, 1, 2 },
			{ 3, 4, 0, 1, 2 }, { 4, 0, 3, 1, 2 }, { 0, 4, 3, 1, 2 }, { 3, 0, 4, 1, 2 }, { 0, 3, 4, 1, 2 }, { 4, 1, 0, 3, 2 }, { 1, 4, 0, 3, 2 },
			{ 4, 0, 1, 3, 2 }, { 0, 4, 1, 3, 2 }, { 1, 0, 4, 3, 2 }, { 0, 1, 4, 3, 2 }, { 3, 1, 0, 4, 2 }, { 1, 3, 0, 4, 2 }, { 3, 0, 1, 4, 2 },
			{ 0, 3, 1, 4, 2 }, { 1, 0, 3, 4, 2 }, { 0, 1, 3, 4, 2 }, { 4, 2, 1, 0, 3 }, { 2, 4, 1, 0, 3 }, { 4, 1, 2, 0, 3 }, { 1, 4, 2, 0, 3 },
			{ 2, 1, 4, 0, 3 }, { 1, 2, 4, 0, 3 }, { 4, 2, 0, 1, 3 }, { 2, 4, 0, 1, 3 }, { 4, 0, 2, 1, 3 }, { 0, 4, 2, 1, 3 }, { 2, 0, 4, 1, 3 },
			{ 0, 2, 4, 1, 3 }, { 4, 1, 0, 2, 3 }, { 1, 4, 0, 2, 3 }, { 4, 0, 1, 2, 3 }, { 0, 4, 1, 2, 3 }, { 1, 0, 4, 2, 3 }, { 0, 1, 4, 2, 3 },
			{ 2, 1, 0, 4, 3 }, { 1, 2, 0, 4, 3 }, { 2, 0, 1, 4, 3 }, { 0, 2, 1, 4, 3 }, { 1, 0, 2, 4, 3 }, { 0, 1, 2, 4, 3 }, { 3, 2, 1, 0, 4 },
			{ 2, 3, 1, 0, 4 }, { 3, 1, 2, 0, 4 }, { 1, 3, 2, 0, 4 }, { 2, 1, 3, 0, 4 }, { 1, 2, 3, 0, 4 }, { 3, 2, 0, 1, 4 }, { 2, 3, 0, 1, 4 },
			{ 3, 0, 2, 1, 4 }, { 0, 3, 2, 1, 4 }, { 2, 0, 3, 1, 4 }, { 0, 2, 3, 1, 4 }, { 3, 1, 0, 2, 4 }, { 1, 3, 0, 2, 4 }, { 3, 0, 1, 2, 4 },
			{ 0, 3, 1, 2, 4 }, { 1, 0, 3, 2, 4 }, { 0, 1, 3, 2, 4 }, { 2, 1, 0, 3, 4 }, { 1, 2, 0, 3, 4 }, { 2, 0, 1, 3, 4 }, { 0, 2, 1, 3, 4 },
			{ 1, 0, 2, 3, 4 }, { 0, 1, 2, 3, 4 } };

	int column1[] = null;

	int column2[] = null;

	int column3[] = null;

	int column4[] = null;

	int column5[] = null;

	/**
	 * Generate all possible solutions based for 5 houses (rows) and 5 properties (columns) and filter solutions based on rules
	 * 
	 * @return List of valid solutions
	 */
	public List<PuzzleSolution> generateValidSolutions() {
		for (int i1 = 0; i1 < 120; i1++) {
			column1 = permutations[i1];
			if (possible(column1, null, null, null, null)) {
				for (int i2 = 0; i2 < 120; i2++) {
					column2 = permutations[i2];
					if (possible(column1, column2, null, null, null)) {
						for (int i3 = 0; i3 < 120; i3++) {
							column3 = permutations[i3];
							if (possible(column1, column2, column3, null, null)) {
								for (int i4 = 0; i4 < 120; i4++) {
									column4 = permutations[i4];
									if (possible(column1, column2, column3, column4, null)) {
										for (int i5 = 0; i5 < 120; i5++) {
											column5 = permutations[i5];
											if (possible(column1, column2, column3, column4, column5))
												solutions.add(toSolution(column1, column2, column3, column4, column5));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return solutions;
	}

	private boolean possible(int[] column1, int[] column2, int[] column3, int[] column4, int[] column5) {
		PuzzleSolution solution = toSolution(column1, column2, column3, column4, column5);
		for (PuzzleRule rule : rules) {
			if (!rule.isPossibleSolution(solution)) {
				return false;
			}
		}
		return true;
	}

	private PuzzleSolution toSolution(int[] column1, int[] column2, int[] column3, int[] column4, int[] column5) {
		PuzzleSolution solution = new PuzzleSolution(5);
		for (int h = 0; h < 5; h++) {
			if (column1 != null)
				solution.getHouse(h).putProperty(keys[0], Property.getValues(keys[0], properties).get(column1[h]));
			if (column2 != null)
				solution.getHouse(h).putProperty(keys[1], Property.getValues(keys[1], properties).get(column2[h]));
			if (column3 != null)
				solution.getHouse(h).putProperty(keys[2], Property.getValues(keys[2], properties).get(column3[h]));
			if (column4 != null)
				solution.getHouse(h).putProperty(keys[3], Property.getValues(keys[3], properties).get(column4[h]));
			if (column5 != null)
				solution.getHouse(h).putProperty(keys[4], Property.getValues(keys[4], properties).get(column5[h]));
		}
		return solution;
	}
}
