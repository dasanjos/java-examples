package com.dasanjos.java.zebraPuzzle.model;

import com.dasanjos.java.util.Property;

public class PuzzleRule {

	private final Position position;

	private final Property subject;

	private final Property predicate;

	public PuzzleRule(String position, Property subject, Property predicate) {
		this.position = PuzzleRule.Position.valueOf(position);
		this.subject = subject;
		this.predicate = predicate;
	}

	public Position getPosition() {
		return position;
	}

	public boolean isValidSolution(PuzzleSolution solution) {
		switch (position) {
			case SAME:
				return verifySameHouse(solution);

			case RIGHT:
				return verifyRightHouse(solution);

			case LEFT:
				return verifyLeftHouse(solution);

			case NEXT:
				return (verifyLeftHouse(solution) || verifyRightHouse(solution));

			default:
				return false;
		}
	}

	protected boolean verifySameHouse(PuzzleSolution solution) {
		for (int h = 0; h < solution.getHousesLenght(); h++) {
			if (subject.getValue().equals(solution.getHouse(h).getProperty(subject.getKey()))
					&& predicate.getValue().equals(solution.getHouse(h).getProperty(predicate.getKey()))) {
				return true;
			}
		}
		return false;
	}

	protected boolean verifyLeftHouse(PuzzleSolution solution) {
		for (int h = 0; h < solution.getHousesLenght() - 1; h++) {
			if (subject.getValue().equals(solution.getHouse(h).getProperty(subject.getKey()))
					&& predicate.getValue().equals(solution.getHouse(h + 1).getProperty(predicate.getKey()))) {
				return true;
			}
		}
		return false;
	}

	protected boolean verifyRightHouse(PuzzleSolution solution) {
		for (int h = 1; h < solution.getHousesLenght(); h++) {
			if (subject.getValue().equals(solution.getHouse(h).getProperty(subject.getKey()))
					&& predicate.getValue().equals(solution.getHouse(h - 1).getProperty(predicate.getKey()))) {
				return true;
			}
		}
		return false;
	}

	public enum Position {
		SAME(), RIGHT(), LEFT(), NEXT();
	}
}