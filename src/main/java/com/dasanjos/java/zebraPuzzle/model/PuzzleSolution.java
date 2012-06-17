package com.dasanjos.java.zebraPuzzle.model;

import java.util.Arrays;
import java.util.List;

public class PuzzleSolution {

	private final House[] houses;

	public PuzzleSolution(int houseNr) {
		this.houses = new House[houseNr];
		for (int h = 0; h < houseNr; h++) {
			houses[h] = new House(h + 1);
		}
	}

	public House getHouse(int houseNr) {
		return houses[houseNr];
	}

	public int getHousesLenght() {
		return houses.length;
	}

	/**
	 * Validate solution with all rules
	 * 
	 * @param rules List of PuzzleRules to validate solution
	 * @return true if valid solution based on rules, false otherwise
	 */
	public boolean isValid(List<PuzzleRule> rules) {
		for (PuzzleRule rule : rules) {
			if (!rule.isValidSolution(this)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(houses);
	}
}