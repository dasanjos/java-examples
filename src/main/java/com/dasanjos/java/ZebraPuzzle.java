package com.dasanjos.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.dasanjos.java.util.PermutationIterator;
import com.dasanjos.java.util.Property;

/**
 * <p>
 * Java implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle)
 * </p>
 * 
 * <b>Definition:</b>
 * 
 * <pre>
 * - There are five houses.
 * - The Englishman lives in the red house.
 * - The Spaniard owns the dog.
 * - Coffee is drunk in the green house.
 * - The Ukrainian drinks tea.
 * - The green house is immediately to the right of the ivory house.
 * - The Old Gold smoker owns snails.
 * - Kools are smoked in the yellow house.
 * - Milk is drunk in the middle house.
 * - The Norwegian lives in the first house.
 * - The man who smokes Chesterfields lives in the house next to the man with the fox.
 * - Kools are smoked in the house next to the house where the horse is kept. [should be "... a house ...", see discussion below]
 * - The Lucky Strike smoker drinks orange juice.
 * - The Japanese smokes Parliaments.
 * - The Norwegian lives next to the blue house.
 * 
 * Now, who drinks water? Who owns the zebra? In the interest of clarity, it must be added that each of the five houses is painted a different color, and their inhabitants are of different national extractions, own different pets, drink different beverages and smoke different brands of American cigarets [sic]. One other thing: in statement 6, right means your right.
 * </pre>
 * 
 * <b>Input file (input.csv)</b> <br />
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
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		// readNrHouses
		// ReadRules
		// CalculateUniqueProperties
		// generateSolutions
		// validateSolutions
	}

	/**
	 * <p>
	 * Generate all possible solutions based on the number of houses (rows) and number of unique properties (columns)
	 * </p>
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
	 *
	 * </pre>
	 * 
	 * 
	 * @param housesNr number of columns (integer)
	 * @param properties list of {@link Property} key value pairs (String)
	 * @return List of all possible solutions
	 */
	public List<Solution> generateSolutions(int housesNr, List<Property> properties) {
		List<Solution> solutions = new ArrayList<ZebraPuzzle.Solution>();
		List<String> keys = Property.getUniqueKeys(properties);

		Integer[] permIndex = new Integer[housesNr]; // Initialize array of possible permutations indexes
		for (int nr = 0; nr < housesNr; nr++) {
			permIndex[nr] = nr;
		}
		// Generate all possible permutations of properties (propNr!)
		PermutationIterator<Integer> permutator = new PermutationIterator<Integer>(permIndex);
		List<Integer[]> permutations = new ArrayList<Integer[]>();
		while (permutator.hasNext()) {
			permutations.add(permutator.next());
		}

		Integer[] combIndex = new Integer[permutations.size()]; // Initialize array of possible combinations indexes
		for (int nr = 0; nr < permutations.size(); nr++) {
			combIndex[nr] = nr;
		}
		// Generate all unique combinations of permutations
		PermutationIterator<Integer> combinator = new PermutationIterator<Integer>(combIndex, housesNr);
		while (combinator.hasNext()) {
			Integer[] combination = combinator.next();

			// Map this combination of Permutations to a Solution
			Solution solution = new Solution(housesNr); // Initialize solution (Nr. of houses is same for each solution)
			for (int nr = 0; nr < housesNr; nr++) {
				solution.houses[nr] = new House(nr + 1, keys.size());
			}
			for (int nr = 0; nr < housesNr; nr++) {
				Integer[] permutation = permutations.get(combination[nr]);
				for (int k = 0; k < permutation.length; k++) {
					solution.houses[nr].properties[k] = new Property(keys.get(k), Property.getValues(keys.get(k), properties).get(permutation[k]));
				}
			}
			System.out.println(solution);
			solutions.add(solution);
		}
		return solutions;
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
			return "Position:" + position + " " + Arrays.toString(properties);
		}
	}

	public class Rule {
		RelativePosition position;

		List<Property> properties;
	}

	public enum RelativePosition {
		SAME(), RIGHT(), LEFT(), NEXT();
	}
}
