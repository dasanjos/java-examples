package com.dasanjos.java.string;

/**
 * String Matching (or search) are a group of algorithms that try to find a
 * place where one or several strings (also called patterns) are found within a
 * larger string or text.
 */
public class StringMatching {

	/**
	 * Naive is a linear search algorithm that checks if a pattern (String size
	 * p) is present inside a text (string size t), returning true if finds a
	 * matching substring of text with pattern input size or false if not found. <br>
	 * Complexity: Worst case O(p*t), Best case O(p), Average case O(p*t)
	 */
	public static boolean naiveSearch(String text, String pattern) {
		int i, j;

		int textLenght = text.length();
		int patternLenght = pattern.length();

		for (i = 0; i < (textLenght - patternLenght); i++) {
			j = 0;
			while (j < patternLenght && text.charAt(i + j) == pattern.charAt(j)) {
				j++;
			}
			if (j == patternLenght) {
				return true;
			}
		}
		return false;
	}
}