package com.dasanjos.java.zebraPuzzle.model;

import java.util.Map;
import java.util.TreeMap;

public class House {
	private final int position;

	private final Map<String, String> properties;

	public House(int position) {
		this.position = position;
		this.properties = new TreeMap<String, String>();
	}

	public void putProperty(String key, String value) {
		properties.put(key, value);
	}

	@Override
	public String toString() {
		return "House" + position + " " + properties.toString();
	}
}