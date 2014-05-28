package com.dasanjos.java.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringHelperTest {

	@Test
	public void reverseString() {
		assertEquals("tupni le", StringHelper.reverse("el input"));

		assertEquals("!daM m'I ,timmaD",
				StringHelper.reverse("Dammit, I'm Mad!"));

		assertEquals("amanaP - lanaC A ,nalP A ,naM A",
				StringHelper.reverse("A Man, A Plan, A Canal - Panama"));
	}

	@Test
	public void reverseStringInPlace() {
		assertEquals("tupni le",
				new String(
						StringHelper.reverseInPlace("el input".toCharArray())));

		assertEquals("!daM m'I ,timmaD",
				new String(StringHelper.reverseInPlace("Dammit, I'm Mad!"
						.toCharArray())));
		
		assertEquals("amanaP - lanaC A ,nalP A ,naM A",
				new String(StringHelper
						.reverseInPlace("A Man, A Plan, A Canal - Panama"
								.toCharArray())));
	}

	@Test
	public void reverseWords() {
		assertEquals("input el", StringHelper.reverseWords("el input"));
		assertEquals("Mad! I'm Dammit,",
				StringHelper.reverseWords("Dammit, I'm Mad!"));
		assertEquals("Panama - Canal A Plan, A Man, A",
				StringHelper.reverseWords("A Man, A Plan, A Canal - Panama"));
	}
}
