package com.dasanjos.java.zebraPuzzle.model;

import java.util.Arrays;

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

	@Override
	public String toString() {
		return Arrays.toString(houses);
	}
}