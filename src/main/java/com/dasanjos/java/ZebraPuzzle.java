package com.dasanjos.java;

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
import com.dasanjos.java.zebraPuzzle.OptimizedFiveHousesAlgorithm;
import com.dasanjos.java.zebraPuzzle.model.House;
import com.dasanjos.java.zebraPuzzle.model.PuzzleRule;
import com.dasanjos.java.zebraPuzzle.model.PuzzleSolution;

/**
 * <p>
 * Java implementation of <a href="http://en.wikipedia.org/wiki/Zebra_Puzzle"> Zebra Puzzle</a> (also called Einstein's Puzzle)
 * </p>
 * 
 * <b>Example input file (input.csv)</b> <br />
 * 
 * <pre>
 * 5
 * SAME,nationality,English,color,Red
 * SAME,nationality,Spaniard,pet,Dog
 * SAME,drink,Coffee,color,Green
 * SAME,drink,Tea,nationality,Ukrainian
 * LEFT,color,Ivory,color,Green
 * SAME,smoke,Old gold,pet,Snails
 * SAME,smoke,Kools,color,Yellow
 * SAME,drink,Milk,position,3
 * SAME,nationality,Norwegian,position,1
 * NEXT,smoke,Chesterfields,pet,Fox
 * NEXT,smoke,Kools,pet,Horse
 * SAME,smoke,Lucky strike,drink,Orange juice
 * SAME,smoke,Parliaments,nationality,Japanese
 * NEXT,color,Blue,nationality,Norwegian
 * SAME,drink,Water
 * SAME,pet,Zebra
 * </pre>
 * 
 * <b>Example output file (output.xml)</b><br />
 * 
 * <pre>
 * &lt;solutions>
 *   &lt;solution>
 *     &lt;house position="1" color="Yellow" nationality="Norwegian" drink="Water"        smoke="Kools"         pet="Fox"/>
 *     &lt;house position="2" color="Blue"   nationality="Ukrainian" drink="Tea"          smoke="Chesterfields" pet="Horse"/>
 *     &lt;house position="3" color="Red"    nationality="English"   drink="Milk"         smoke="Old gold"      pet="Snails"/>
 *     &lt;house position="4" color="Ivory"  nationality="Spaniard"  drink="Orange juice" smoke="Lucky strike"  pet="Dog"/>
 *     &lt;house position="5" color="Green"  nationality="Japanese"  drink="Coffee"       smoke="Parliaments"   pet="Zebra"/>
 *   &lt;/solution>
 * &lt;/solutions>
 * </pre>
 */
public abstract class ZebraPuzzle {

	protected int houses;

	protected List<Property> properties = new ArrayList<Property>();;

	protected List<PuzzleRule> rules = new ArrayList<PuzzleRule>();;

	protected List<PuzzleSolution> solutions = new ArrayList<PuzzleSolution>();

	protected String[] keys;

	/**
	 * Parse input file and generate internal lists of properties, keys and rules for solution generation and validation
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public void parseInputCSV(String path) throws FileNotFoundException {
		CSVReader reader = new CSVReader(path);
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
		// Set list of columns (unique keys of properties)
		keys = Property.getUniqueKeys(properties).toArray(new String[this.houses]);
	}

	public abstract List<PuzzleSolution> generateValidSolutions();

	/**
	 * Create XML output file (with link to template.xsl) for puzzle solutions
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
						output.write("  <tr><th>" + key + "</th>" + eol);
						output.write("    <xsl:for-each select=\"house/@" + key + "\">" + eol);
						output.write("      <td><xsl:value-of select=\".\"/></td>" + eol);
						output.write("    </xsl:for-each>" + eol);
						output.write("  </tr>" + eol);
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

	public static void main(String[] args) throws Exception {
		// Validate args for required paramenters (file paths) input.csv and output.xml
		if (args.length < 2) {
			System.out.println("Required arguments missing: Path to input CSV file and output XML file.");
			System.out.println("Usage: java -jar ZebraPuzzle.jar path-to-input.csv path-to-output.xml");
			System.exit(-1);
		}

		// Generic implementation for puzzle with 1 to 4 houses
		// ZebraPuzzle puzzle = new GenericBruteForceAlgorithm();
		
		// Optimized implementation for puzzle with 5 houses
		ZebraPuzzle puzzle = new OptimizedFiveHousesAlgorithm();
		puzzle.parseInputCSV(args[0]);
		puzzle.generateValidSolutions();
		puzzle.writeXMLOutput(args[1]);
		puzzle.writeXlsOutput(args[1]);
	}
}
