package com.dasanjos.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO Remove and move methods to House
public class Property {
	private final String key;

	private final String value;

	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static List<String> getUniqueKeys(List<Property> properties) {
		List<String> keys = new ArrayList<String>();
		for (Property property : properties) {
			if (!keys.contains(property.key)) {
				keys.add(property.key);
			}
		}
		Collections.sort(keys);
		return keys;
	}

	public static List<String> getValues(String key, List<Property> properties) {
		List<String> values = new ArrayList<String>();
		for (Property property : properties) {
			if (key.equals(property.key)) {
				values.add(property.value);
			}
		}
		Collections.sort(values);
		return values;
	}

	@Override
	public String toString() {
		return key + ":" + value;
	}

	@Override
	public boolean equals(Object o) {
		return (o != null && o instanceof Property && key.equals(((Property) o).key) && value.equals(((Property) o).value));
	}

	@Override
	public int hashCode() {
		return key.hashCode() ^ value.hashCode();
	}
}
