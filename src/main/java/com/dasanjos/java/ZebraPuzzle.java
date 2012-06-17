package com.dasanjos.java;

import java.io.File;
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

import com.dasanjos.java.zebraPuzzle.BruteForceSolver;
import com.dasanjos.java.zebraPuzzle.model.House;
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
public class ZebraPuzzle {

	public static void main(String[] args) throws Exception {
		// TODO Validate args for paramenters (filepaths) input.csv and output.xml
		validateParams(args);

		// Parse Input
		BruteForceSolver puzzle = new BruteForceSolver(args[0]);

		// Generate all valid Solutions
		List<PuzzleSolution> solutions = puzzle.generateValidSolutions();

		writeXMLOutput(solutions, args[1]);
	}

	private static void validateParams(String[] args) {
		if (args.length < 2) {
			System.out.println("Required arguments missing: Path to input CSV file and output XML file.");
			System.out.println("Usage: java -jar ZebraPuzzle.jar path-to-input.csv path-to-output.xml");
			System.exit(-1);
		}
	}

	private static void writeXMLOutput(List<PuzzleSolution> solutions, String path) throws Exception {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		doc.setXmlStandalone(true);

		ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"zebra.xsl\"");
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
}
