package com.dasanjos.java.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.util.TimeTracker;

public class PatternMatchingTest extends TimeTracker {

	String input;
	String pattern;

	@Before
	public void setup() {
		input = "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem";
		pattern = "lorem ipsum";
	}

	@Test
	public void javaStringContains() {
		Assert.assertTrue(input.contains(pattern));
	}

	@Test
	public void javaStringMatches() {
		Assert.assertTrue(input.matches(".*" + pattern + ".*"));
	}

	@Test
	public void naiveSearch() {
		Assert.assertTrue(StringMatching.naiveSearch(input, pattern));
	}

	// @Test
	// public void javaRegex() {
	// Pattern pattern = Pattern.compile("/\\{\\w+\\}/");
	// Matcher matcher = pattern.matcher(URL);
	// if (matcher.find()) {
	// System.out.println(matcher.group(0));
	// } else {
	// System.out.println("Match not found");
	// }
	// }
}
