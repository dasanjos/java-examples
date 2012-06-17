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
import com.dasanjos.java.util.math.PermutationIterator;
import com.dasanjos.java.util.math.PermutationWithRepetitionIterator;
import com.dasanjos.java.zebraPuzzle.model.House;
import com.dasanjos.java.zebraPuzzle.model.PuzzleRule;
import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

/**
 * Java Brute Force implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle) <br />
 * Generate solutions within seconds for puzzles with 1 to 4 houses, but it's impractical (hours) for 5 or more houses.
 */
public class BruteForceSolver {

	protected int houses;

	protected List<Property> properties;

	protected List<PuzzleRule> rules;

	protected List<PuzzleSolution> solutions;

	public BruteForceSolver(String path) throws FileNotFoundException {
		this(new CSVReader(path));
	}

	public BruteForceSolver(CSVReader reader) {
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
	}

	/**
	 * Generate all possible solutions based on the nr. of houses (rows) and nr. of properties (columns) and filter solutions based on rules
	 * 
	 * @return List of valid solutions
	 */
	public void generateValidSolutions() {
		solutions = new ArrayList<PuzzleSolution>();
		List<String> keys = Property.getUniqueKeys(properties);

		// Initialize array of possible permutations indexes
		Integer[] permIndex = new Integer[houses];
		for (int nr = 0; nr < houses; nr++) {
			permIndex[nr] = nr;
		}

		// Generate all permutations without repetition of properties (propNr!)
		PermutationIterator<Integer> propPermutator = new PermutationIterator<Integer>(permIndex);
		List<Integer[]> propPermutations = new ArrayList<Integer[]>();
		while (propPermutator.hasNext()) {
			propPermutations.add(propPermutator.next());
		}

		// Initialize array of possible combinations indexes
		Integer[] solutionIndex = new Integer[propPermutations.size()];
		for (int nr = 0; nr < propPermutations.size(); nr++) {
			solutionIndex[nr] = nr;
		}

		// Generate all permutations with repetition of previous permutations
		PermutationWithRepetitionIterator<Integer> solPermutator = new PermutationWithRepetitionIterator<Integer>(solutionIndex, houses);
		while (solPermutator.hasNext()) {
			// Map this combination of Permutations to a Solution
			Integer[] solPermutation = solPermutator.next();
			PuzzleSolution solution = new PuzzleSolution(houses);
			for (int nr = 0; nr < houses; nr++) {
				Integer[] propPermutation = propPermutations.get(solPermutation[nr]);
				for (int k = 0; k < propPermutation.length; k++) {
					String key = keys.get(nr);
					solution.getHouse(k).putProperty(key, Property.getValues(key, properties).get(propPermutation[k]));
				}
			}
			if (solution.isValid(rules)) {
				solutions.add(solution);
			}
		}
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
	public void generateXlsOutput(String path) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader("src/main/resources/template.xsl"));
		Writer output = new BufferedWriter(new FileWriter(new File(path).getAbsoluteFile().getParent() + File.separator + "template.xsl"));
		try {
			String line = null;
			String eol = System.getProperty("line.separator");
			while ((line = input.readLine()) != null) {
				if (line.contains("<!--PROPERTIES-->")) {
					List<String> keys = Property.getUniqueKeys(properties);
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
