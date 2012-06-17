package com.dasanjos.java.zebraPuzzle.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class House {

	private final Map<String, String> properties;

	public House(int position) {
		this.properties = new TreeMap<String, String>();
		this.properties.put("position", String.valueOf(position));
	}

	public void putProperty(String key, String value) {
		properties.put(key, value);

	}

	public String getProperty(String key) {
		return properties.get(key);
	}

	public Set<String> getKeys() {
		return properties.keySet();
	}

	@Override
	public String toString() {
		return properties.toString();
	}
}