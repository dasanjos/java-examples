package com.dasanjos.java.zebraPuzzle.model;

import java.util.ArrayList;
import java.util.List;

import com.dasanjos.java.util.Property;

public class PuzzleRule {
	
	private final HousePosition position;

	private final List<Property> properties;

	public PuzzleRule(HousePosition position) {
		this.position = position;
		this.properties = new ArrayList<Property>();
	}

	public void addProperty(Property property) {
		this.properties.add(property);
	}

	public HousePosition getPosition() {
		return position;
	}
}