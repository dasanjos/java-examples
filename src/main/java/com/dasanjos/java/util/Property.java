package com.dasanjos.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Property {
	private String key;

	private String value;

	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
}
