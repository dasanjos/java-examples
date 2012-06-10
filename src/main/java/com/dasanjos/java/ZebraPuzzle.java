package com.dasanjos.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dasanjos.java.util.Property;
import com.dasanjos.java.util.file.CSVReader;
import com.dasanjos.java.util.math.PermutationIterator;
import com.dasanjos.java.util.math.PermutationWithRepetitionIterator;

/**
 * <p>
 * Java implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle)
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

	int houses;

	List<Property> properties;

	List<Rule> rules;

	public ZebraPuzzle(File input) throws FileNotFoundException {
		this(new CSVReader(input, ","));
	}

	public ZebraPuzzle(CSVReader reader) {
		properties = new ArrayList<Property>();
		rules = new ArrayList<Rule>();
		parseInputCSV(reader);
	}

	/**
	 * Parse input values and generate internal lists of unique Properties for solution generation and Rules for solution validation
	 * 
	 * @param input CSV File with Zebra Puzzle input content
	 */
	private void parseInputCSV(CSVReader reader) {
		List<String> values;

		// Read Number of Houses
		values = reader.readLine();
		this.houses = Integer.parseInt(values.get(0));

		// Read Rules and Calculate Unique Properties
		while ((values = reader.readLine()) != null) {
			int i = 0;
			Rule rule = new Rule(RelativePosition.valueOf(values.get(i++)));
			while (i < values.size()) {
				Property property = new Property(values.get(i++), values.get(i++));
				rule.addProperty(property);
				if (!properties.contains(property)) {
					properties.add(property);
				}
			}
			rules.add(rule);
		}
	}

	/**
	 * Generate all possible solutions based on the number of houses (rows) and number of unique properties (columns)
	 */
	public List<Solution> generateSolutions() {
		List<Solution> solutions = new ArrayList<ZebraPuzzle.Solution>();
		List<String> keys = Property.getUniqueKeys(properties);

		Integer[] permIndex = new Integer[houses]; // Initialize array of possible permutations indexes
		for (int nr = 0; nr < houses; nr++) {
			permIndex[nr] = nr;
		}
		// Generate all permutations without repetition of properties (propNr!)
		PermutationIterator<Integer> propPermutator = new PermutationIterator<Integer>(permIndex);
		List<Integer[]> propPermutations = new ArrayList<Integer[]>();
		while (propPermutator.hasNext()) {
			propPermutations.add(propPermutator.next());
		}

		Integer[] solutionIndex = new Integer[propPermutations.size()]; // Initialize array of possible combinations indexes
		for (int nr = 0; nr < propPermutations.size(); nr++) {
			solutionIndex[nr] = nr;
		}
		// Generate all permutations with repetition of previous permutations
		PermutationWithRepetitionIterator<Integer> solPermutator = new PermutationWithRepetitionIterator<Integer>(solutionIndex, houses);
		while (solPermutator.hasNext()) {
			Integer[] solPermutation = solPermutator.next();

			// Map this combination of Permutations to a Solution
			Solution solution = new Solution(houses); // Initialize solution (Nr. of houses is same for each solution)
			for (int nr = 0; nr < houses; nr++) {
				solution.houses[nr] = new House(nr + 1, keys.size());
			}
			for (int nr = 0; nr < houses; nr++) {
				Integer[] propPermutation = propPermutations.get(solPermutation[nr]);
				for (int k = 0; k < propPermutation.length; k++) {
					String key = keys.get(nr);
					solution.houses[k].properties[nr] = new Property(key, Property.getValues(key, properties).get(propPermutation[k]));
				}
			}
			solutions.add(solution);
		}
		return solutions;
	}

	// TODO Add Comment
	private List<Solution> getValidSolutions(List<Solution> possibleSolutions) {
		List<Solution> solutions = new ArrayList<ZebraPuzzle.Solution>();

		return solutions;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Validate args for paramenters (filepaths) input.csv and output.xml
		File input = new File(args[0]);

		// Parse Input
		ZebraPuzzle puzzle = new ZebraPuzzle(input);

		// Generate all Possible Solutions
		List<Solution> possibleSolutions = puzzle.generateSolutions();

		// validateSolutions
		List<Solution> solutions = puzzle.getValidSolutions(possibleSolutions);

		for (Solution solution : solutions) {
			System.out.println(solution);
		}
	}

	public class Solution {
		House[] houses;

		public Solution(int houseNr) {
			this.houses = new House[houseNr];
		}

		@Override
		public String toString() {
			return Arrays.toString(houses);
		}
	}

	public class House {
		int position;

		Property[] properties;

		public House(int position, int propertyNr) {
			this.position = position;
			this.properties = new Property[propertyNr];
		}

		@Override
		public String toString() {
			return "House" + position + " " + Arrays.toString(properties);
		}
	}

	public class Rule {
		final RelativePosition position;

		final List<Property> properties;

		public Rule(RelativePosition position) {
			this.position = position;
			this.properties = new ArrayList<Property>();
		}

		public void addProperty(Property property) {
			this.properties.add(property);
		}
	}

	public enum RelativePosition {
		SAME(), RIGHT(), LEFT(), NEXT();
	}
}
