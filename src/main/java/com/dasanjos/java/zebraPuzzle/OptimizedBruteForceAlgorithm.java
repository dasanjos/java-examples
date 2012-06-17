package com.dasanjos.java.zebraPuzzle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

import com.dasanjos.java.util.Property;
import com.dasanjos.java.util.file.CSVReader;
import com.dasanjos.java.zebraPuzzle.model.House;
import com.dasanjos.java.zebraPuzzle.model.PuzzleRule;
import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

/**
 * Optimized implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle) <br />
 * Generate solutions pre-filling values from clues
 */
public class OptimizedBruteForceAlgorithm {

	protected int houses;

	protected List<Property> properties;

	protected List<PuzzleRule> rules;

	protected List<PuzzleSolution> solutions;

	int permutations[][] = { { 4, 3, 2, 1, 0 }, { 3, 4, 2, 1, 0 }, { 4, 2, 3, 1, 0 }, { 2, 4, 3, 1, 0 }, { 3, 2, 4, 1, 0 }, { 2, 3, 4, 1, 0 },
			{ 4, 3, 1, 2, 0 }, { 3, 4, 1, 2, 0 }, { 4, 1, 3, 2, 0 }, { 1, 4, 3, 2, 0 }, { 3, 1, 4, 2, 0 }, { 1, 3, 4, 2, 0 }, { 4, 2, 1, 3, 0 },
			{ 2, 4, 1, 3, 0 }, { 4, 1, 2, 3, 0 }, { 1, 4, 2, 3, 0 }, { 2, 1, 4, 3, 0 }, { 1, 2, 4, 3, 0 }, { 3, 2, 1, 4, 0 }, { 2, 3, 1, 4, 0 },
			{ 3, 1, 2, 4, 0 }, { 1, 3, 2, 4, 0 }, { 2, 1, 3, 4, 0 }, { 1, 2, 3, 4, 0 }, { 4, 3, 2, 0, 1 }, { 3, 4, 2, 0, 1 }, { 4, 2, 3, 0, 1 },
			{ 2, 4, 3, 0, 1 }, { 3, 2, 4, 0, 1 }, { 2, 3, 4, 0, 1 }, { 4, 3, 0, 2, 1 }, { 3, 4, 0, 2, 1 }, { 4, 0, 3, 2, 1 }, { 0, 4, 3, 2, 1 },
			{ 3, 0, 4, 2, 1 }, { 0, 3, 4, 2, 1 }, { 4, 2, 0, 3, 1 }, { 2, 4, 0, 3, 1 }, { 4, 0, 2, 3, 1 }, { 0, 4, 2, 3, 1 }, { 2, 0, 4, 3, 1 },
			{ 0, 2, 4, 3, 1 }, { 3, 2, 0, 4, 1 }, { 2, 3, 0, 4, 1 }, { 3, 0, 2, 4, 1 }, { 0, 3, 2, 4, 1 }, { 2, 0, 3, 4, 1 }, { 0, 2, 3, 4, 1 },
			{ 4, 3, 1, 0, 2 }, { 3, 4, 1, 0, 2 }, { 4, 1, 3, 0, 2 }, { 1, 4, 3, 0, 2 }, { 3, 1, 4, 0, 2 }, { 1, 3, 4, 0, 2 }, { 4, 3, 0, 1, 2 },
			{ 3, 4, 0, 1, 2 }, { 4, 0, 3, 1, 2 }, { 0, 4, 3, 1, 2 }, { 3, 0, 4, 1, 2 }, { 0, 3, 4, 1, 2 }, { 4, 1, 0, 3, 2 }, { 1, 4, 0, 3, 2 },
			{ 4, 0, 1, 3, 2 }, { 0, 4, 1, 3, 2 }, { 1, 0, 4, 3, 2 }, { 0, 1, 4, 3, 2 }, { 3, 1, 0, 4, 2 }, { 1, 3, 0, 4, 2 }, { 3, 0, 1, 4, 2 },
			{ 0, 3, 1, 4, 2 }, { 1, 0, 3, 4, 2 }, { 0, 1, 3, 4, 2 }, { 4, 2, 1, 0, 3 }, { 2, 4, 1, 0, 3 }, { 4, 1, 2, 0, 3 }, { 1, 4, 2, 0, 3 },
			{ 2, 1, 4, 0, 3 }, { 1, 2, 4, 0, 3 }, { 4, 2, 0, 1, 3 }, { 2, 4, 0, 1, 3 }, { 4, 0, 2, 1, 3 }, { 0, 4, 2, 1, 3 }, { 2, 0, 4, 1, 3 },
			{ 0, 2, 4, 1, 3 }, { 4, 1, 0, 2, 3 }, { 1, 4, 0, 2, 3 }, { 4, 0, 1, 2, 3 }, { 0, 4, 1, 2, 3 }, { 1, 0, 4, 2, 3 }, { 0, 1, 4, 2, 3 },
			{ 2, 1, 0, 4, 3 }, { 1, 2, 0, 4, 3 }, { 2, 0, 1, 4, 3 }, { 0, 2, 1, 4, 3 }, { 1, 0, 2, 4, 3 }, { 0, 1, 2, 4, 3 }, { 3, 2, 1, 0, 4 },
			{ 2, 3, 1, 0, 4 }, { 3, 1, 2, 0, 4 }, { 1, 3, 2, 0, 4 }, { 2, 1, 3, 0, 4 }, { 1, 2, 3, 0, 4 }, { 3, 2, 0, 1, 4 }, { 2, 3, 0, 1, 4 },
			{ 3, 0, 2, 1, 4 }, { 0, 3, 2, 1, 4 }, { 2, 0, 3, 1, 4 }, { 0, 2, 3, 1, 4 }, { 3, 1, 0, 2, 4 }, { 1, 3, 0, 2, 4 }, { 3, 0, 1, 2, 4 },
			{ 0, 3, 1, 2, 4 }, { 1, 0, 3, 2, 4 }, { 0, 1, 3, 2, 4 }, { 2, 1, 0, 3, 4 }, { 1, 2, 0, 3, 4 }, { 2, 0, 1, 3, 4 }, { 0, 2, 1, 3, 4 },
			{ 1, 0, 2, 3, 4 }, { 0, 1, 2, 3, 4 } };

	int column1[] = null;

	int column2[] = null;

	int column3[] = null;

	int column4[] = null;

	int column5[] = null;

	private String[] keys = new String[5];

	public OptimizedBruteForceAlgorithm(String path) throws FileNotFoundException {
		this(new CSVReader(path));
	}

	public OptimizedBruteForceAlgorithm(CSVReader reader) {
		properties = new ArrayList<Property>();
		rules = new ArrayList<PuzzleRule>();
		parseInputCSV(reader);
	}

	/**
	 * Parse input values and generate internal lists of unique Properties for solution generation and Rules for solution validation
	 * 
	 * @param input CSV File with Zebra Puzzle input content
	 */
	private void parseInputCSV(CSVReader reader) {
		// Read Number of Houses
		List<String> values = reader.readLine();
		this.houses = Integer.parseInt(values.get(0));

		// Read Rules and Calculate Unique Properties
		while ((values = reader.readLine()) != null) {
			Property property1 = new Property(values.get(1), values.get(2));
			if (!"position".equals(property1.getKey()) && !properties.contains(property1)) {
				properties.add(property1);
			}
			if (values.size() == 5) {
				Property property2 = new Property(values.get(3), values.get(4));
				if (!"position".equals(property2.getKey()) && !properties.contains(property2)) {
					properties.add(property2);
				}
				rules.add(new PuzzleRule(values.get(0), property1, property2));
			}
		}

		List<String> uniqueKeys = Property.getUniqueKeys(properties);
		int i = 0;
		for (String key : uniqueKeys) {
			keys[i++] = key;
		}
	}

	/**
	 * Generate all possible solutions based for 5 houses (rows) and 5 properties (columns) and filter solutions based on rules
	 * 
	 * @return List of valid solutions
	 */
	public void generateValidSolutions() {
		solutions = new ArrayList<PuzzleSolution>();

		for (int i1 = 0; i1 < 120; i1++) {
			column1 = permutations[i1];
			if (possible(column1, null, null, null, null)) {
				for (int i2 = 0; i2 < 120; i2++) {
					column2 = permutations[i2];
					if (possible(column1, column2, null, null, null)) {
						for (int i3 = 0; i3 < 120; i3++) {
							column3 = permutations[i3];
							if (possible(column1, column2, column3, null, null)) {
								for (int i4 = 0; i4 < 120; i4++) {
									column4 = permutations[i4];
									if (possible(column1, column2, column3, column4, null)) {
										for (int i5 = 0; i5 < 120; i5++) {
											column5 = permutations[i5];
											if (possible(column1, column2, column3, column4, column5))
												solutions.add(toSolution(column1, column2, column3, column4, column5));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean possible(int[] column1, int[] column2, int[] column3, int[] column4, int[] column5) {
		PuzzleSolution solution = toSolution(column1, column2, column3, column4, column5);
		for (PuzzleRule rule : rules) {
			if (!rule.isPossibleSolution(solution)) {
				return false;
			}
		}
		return true;
	}

	private PuzzleSolution toSolution(int[] column1, int[] column2, int[] column3, int[] column4, int[] column5) {
		PuzzleSolution solution = new PuzzleSolution(5);

		for (int h = 0; h < 5; h++) {
			if (column1 != null)
				solution.getHouse(h).putProperty(keys[0], Property.getValues(keys[0], properties).get(column1[h]));
			if (column2 != null)
				solution.getHouse(h).putProperty(keys[1], Property.getValues(keys[1], properties).get(column2[h]));
			if (column3 != null)
				solution.getHouse(h).putProperty(keys[2], Property.getValues(keys[2], properties).get(column3[h]));
			if (column4 != null)
				solution.getHouse(h).putProperty(keys[3], Property.getValues(keys[3], properties).get(column4[h]));
			if (column5 != null)
				solution.getHouse(h).putProperty(keys[4], Property.getValues(keys[4], properties).get(column5[h]));
		}
		return solution;
	}

	/**
	 * Create XML output file (with link to zebra.xls) for puzzle solutions
	 * 
	 * @param path path to output XML file
	 * @throws Exception
	 */
	public void writeXMLOutput(String path) throws Exception {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		doc.setXmlStandalone(true);

		ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"template.xsl\"");
		doc.appendChild(pi);

		Element rootElement = doc.createElement("solutions");
		doc.appendChild(rootElement);

		for (PuzzleSolution solution : solutions) {
			Element solutionNode = doc.createElement("solution");
			rootElement.appendChild(solutionNode);

			for (int h = 0; h < solution.getHousesLenght(); h++) {
				House house = solution.getHouse(h);

				Element houseNode = doc.createElement("house");
				rootElement.appendChild(solutionNode);

				Set<String> keys = house.getKeys();
				for (String key : keys) {
					Attr attr = doc.createAttribute(key);
					attr.setValue(house.getProperty(key));
					houseNode.setAttributeNode(attr);
				}
				solutionNode.appendChild(houseNode);
			}
		}

		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.transform(new DOMSource(doc), new StreamResult(new File(path)));
	}

	/**
	 * Generate XLS transformation based on input properties and template.xsl (from main/resources)
	 * 
	 * @param path path to output.xml to store template.xsl in same directory
	 * @throws IOException
	 */
	public void writeXlsOutput(String path) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("src/main/resources/template.xsl"));
		Writer output = new BufferedWriter(new FileWriter(new File(path).getAbsoluteFile().getParent() + File.separator + "template.xsl"));
		try {
			String line = null;
			String eol = System.getProperty("line.separator");
			while ((line = input.readLine()) != null) {
				if (line.contains("<!--PROPERTIES-->")) {
					for (String key : keys) {
						output.write("<tr><th>" + key + "</th>" + eol);
						output.write("  <xsl:for-each select=\"house/@" + key + "\">" + eol);
						output.write("      <td><xsl:value-of select=\".\"/></td>" + eol);
						output.write("  </xsl:for-each>" + eol);
						output.write("</tr>" + eol);
					}
				} else {
					output.write(line + eol);
				}
			}
		} finally {
			input.close();
			output.close();
		}
	}
}
