package com.dasanjos.java.string;

public class StringHelper {

	public static String reverse(String input) {
		StringBuilder result = new StringBuilder();
		for (int i = input.length() - 1; i >= 0; i--) {
			result.append(input.charAt(i));
		}
		return result.toString();
	}

	public static char[] reverseInPlace(char[] input) {
		int length = input.length, last = length - 1;
		for (int i = 0; i < length / 2; i++) {
			// StringHelper.swap(input, i, last);
			char c = input[i];
			input[i] = input[last - i];
			input[last - i] = c;
		}
		return input;
	}

	public static String reverseWords(String input) {
		StringBuilder result = new StringBuilder();
		String[] words = input.split(" ");
		for (int i = words.length - 1; i >= 0; i--) {
			result.append(words[i] + (i == 0 ? "" : " "));
		}
		return result.toString();
	}
}
