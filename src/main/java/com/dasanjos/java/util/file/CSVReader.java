package com.dasanjos.java.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CSVReader {

	private final String delim;

	private final BufferedReader reader;

	/**
	 * Parses a CSV file with configurable list of delimiters
	 * 
	 * @param file CSV file to be parsed
	 * @param delim String with delimiter used in CSV file
	 * @throws FileNotFoundException
	 */
	public CSVReader(File file, String delim) throws FileNotFoundException {
		this.delim = delim;
		this.reader = new BufferedReader(new FileReader(file));
	}

	/**
	 * Parses a CSV file content (String) with configurable list of delimiters
	 * 
	 * @param content String with CSV file contents to be parsed
	 * @param delim String with delimiter used in CSV file
	 */
	public CSVReader(String content, String delim) {
		this.delim = delim;
		this.reader = new BufferedReader(new StringReader(content));
	}

	/**
	 * Parses next line of CSV file for values
	 * 
	 * @return List<String> of values on current line or null if EOF
	 */
	public List<String> readLine() {
		List<String> values = null;
		try {
			String strLine = reader.readLine();
			StringTokenizer st = null;

			if (strLine != null) {
				st = new StringTokenizer(strLine, delim);
				values = new ArrayList<String>();

				while (st.hasMoreTokens()) {
					values.add(st.nextToken());
				}
			}
		} catch (IOException e) {
			// TODO Add logging
		}
		return values;
	}
}
