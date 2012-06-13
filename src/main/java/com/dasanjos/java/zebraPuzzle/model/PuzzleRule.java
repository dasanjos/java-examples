package com.dasanjos.java.zebraPuzzle.model;

import java.util.ArrayList;
import java.util.List;

import com.dasanjos.java.util.Property;

public class PuzzleRule {

	private final Position position;

	private final List<Property> properties;

	public PuzzleRule(String position) {
		this.position = PuzzleRule.Position.valueOf(position);
		this.properties = new ArrayList<Property>();
	}

	public void addProperty(Property property) {
		this.properties.add(property);
	}

	public Position getPosition() {
		return position;
	}

	public boolean isValidSolution(PuzzleSolution solution) {
		boolean valid = false;

		switch (position) {
			case SAME:
				valid = verifySameHouse(solution);
				break;

			case RIGHT:
				valid = verifyRightHouse(solution);
				break;

			case LEFT:
				valid = verifyLeftHouse(solution);
				break;

			case NEXT:
				valid = (verifyRightHouse(solution) || verifyLeftHouse(solution));
				break;

			default:
				valid = false;
				break;
		}
		return valid;
	}

	private boolean verifySameHouse(PuzzleSolution solution) {
		for (int h = 0; h < solution.getHousesLenght(); h++) {
			if (solution.getHouse(h).getProperty(properties.get(0).getKey()).equals(properties.get(0).getValue())
					&& solution.getHouse(h).getProperty(properties.get(1).getKey()).equals(properties.get(1).getValue())) {
				return true;
			}
		}
		return false;
	}

	private boolean verifyRightHouse(PuzzleSolution solution) {
		for (int h = 0; h < solution.getHousesLenght() - 1; h++) {
			if (solution.getHouse(h).getProperty(properties.get(0).getKey()).equals(properties.get(0).getValue())
					&& solution.getHouse(h + 1).getProperty(properties.get(1).getKey()).equals(properties.get(1).getValue())) {
				return true;
			}
		}
		return false;
	}

	private boolean verifyLeftHouse(PuzzleSolution solution) {
		for (int h = 1; h < solution.getHousesLenght(); h++) {
			if (solution.getHouse(h).getProperty(properties.get(0).getKey()).equals(properties.get(0).getValue())
					&& solution.getHouse(h - 1).getProperty(properties.get(1).getKey()).equals(properties.get(1).getValue())) {
				return true;
			}
		}
		return false;
	}

	public enum Position {
		SAME(), RIGHT(), LEFT(), NEXT();
	}
}