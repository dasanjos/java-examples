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

	public boolean isPossibleSolution(PuzzleSolution solution) {
		switch (position) {
			case SAME:
				return verifySamePossible(solution);

			case LEFT:
				return verifyLeftPossible(solution);

			case RIGHT:
				return verifyRightPossible(solution);

			case NEXT:
				return (verifyLeftPossible(solution) || verifyRightPossible(solution));

			default:
				return false;
		}
	}

	private boolean verifySameHouse(PuzzleSolution solution) {
		for (int h = 0; h < solution.getHousesLenght(); h++) {
			if (subject.getValue().equals(solution.getHouse(h).getProperty(subject.getKey()))
					&& predicate.getValue().equals(solution.getHouse(h).getProperty(predicate.getKey()))) {
				return true;
			}
		}
		return false;
	}

	private boolean verifySamePossible(PuzzleSolution solution) {
		boolean isPossible = false;
		for (int h = 0; h < solution.getHousesLenght(); h++) {
			String solSub = solution.getHouse(h).getProperty(subject.getKey());
			String solPred = solution.getHouse(h).getProperty(predicate.getKey());
			if ((solSub != null) && (solPred != null)) {
				if ((subject.getValue().equals(solSub)) && predicate.getValue().equals(solPred)) {
					isPossible = true;
				}
			} else {
				isPossible = true;
			}
		}
		return isPossible;
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

	private boolean verifyLeftPossible(PuzzleSolution solution) {
		boolean isPossible = false;
		for (int h = 0; h < solution.getHousesLenght() - 1; h++) {
			String solSub = solution.getHouse(h).getProperty(subject.getKey());
			String solPred = solution.getHouse(h + 1).getProperty(predicate.getKey());
			if ((solSub != null) && (solPred != null)) {
				if ((subject.getValue().equals(solSub)) && predicate.getValue().equals(solPred)) {
					isPossible = true;
				}
			} else {
				isPossible = true;
			}
		}
		return isPossible;
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

	private boolean verifyRightPossible(PuzzleSolution solution) {
		boolean isPossible = false;
		for (int h = 1; h < solution.getHousesLenght() - 1; h++) {
			String solSub = solution.getHouse(h).getProperty(subject.getKey());
			String solPred = solution.getHouse(h - 1).getProperty(predicate.getKey());
			if ((solSub != null) && (solPred != null)) {
				if ((subject.getValue().equals(solSub)) && predicate.getValue().equals(solPred)) {
					isPossible = true;
				}
			} else {
				isPossible = true;
			}
		}
		return isPossible;
	}

	public enum Position {
		SAME(), RIGHT(), LEFT(), NEXT();
	}
}