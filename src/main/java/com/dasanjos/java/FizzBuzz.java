package com.dasanjos.java;

/**
 * <p>
 * Java implementation of <a href="http://en.wikipedia.org/wiki/Bizz_buzz">Fizz Buzz</a> (also called Bizz Buzz)
 * </p>
 * 
 * <b>Definition:</b>
 * 
 * <pre>
 * Write a program that prints the numbers from 1 to 100. 
 * But for multiples of three print "Fizz" instead of the number and for the multiples of five
 * print "Buzz". For numbers which are multiples of both three and five print "FizzBuzz".
 * </pre>
 */
public class FizzBuzz {

	/**
	 * Calculates the FizzBuzz string for a given number n
	 * 
	 * @param n the n-th FizzBuzz number (long)
	 * @return the FizzBuzz string
	 */
	public static String calculate(long n) {
		String result = "";
		if (n % 3 == 0) {
			result += "Fizz";
		}
		if (n % 5 == 0) {
			result += "Buzz";
		}
		if (result.isEmpty()) {
			result = String.valueOf(n);
		}
		return result + "\n";
	}

	public static void main(String args[]) {
		for (long i = 1; i <= 100; i++) {
			System.out.print(calculate(i));
		}
	}
}
