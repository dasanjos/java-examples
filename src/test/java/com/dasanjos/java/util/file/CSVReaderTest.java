package com.dasanjos.java.util.file;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CSVReaderTest {

	@Test
	public void readEmptyStringReturnsNull() {
		CSVReader reader = new CSVReader("", ";");

		Assert.assertEquals(null, reader.readLine());
	}

	@Test
	public void readSingleValueString() {
		CSVReader reader = new CSVReader("5", ";");

		List<String> values = reader.readLine();
		Assert.assertEquals(1, values.size());
		Assert.assertEquals("5", values.get(0));

		Assert.assertEquals(null, reader.readLine());
	}

	@Test
	public void readMultiValueString() {
		CSVReader reader = new CSVReader("5;value;otherValue", ";");

		List<String> values = reader.readLine();
		Assert.assertEquals(3, values.size());
		Assert.assertEquals("5", values.get(0));
		Assert.assertEquals("value", values.get(1));
		Assert.assertEquals("otherValue", values.get(2));

		Assert.assertEquals(null, reader.readLine());
	}

	@Test
	public void readMultiLineString() {
		CSVReader reader = new CSVReader("5\nvalue\notherValue", ";");

		List<String> values = reader.readLine();
		Assert.assertEquals(1, values.size());
		Assert.assertEquals("5", values.get(0));

		values = reader.readLine();
		Assert.assertEquals(1, values.size());
		Assert.assertEquals("value", values.get(0));

		values = reader.readLine();
		Assert.assertEquals(1, values.size());
		Assert.assertEquals("otherValue", values.get(0));

		Assert.assertEquals(null, reader.readLine());
	}

	@Test
	public void readMultiLineMultiValueString() {
		CSVReader reader = new CSVReader("5\nvalue;otherValue\nYear;2012\n", ";");

		List<String> values = reader.readLine();
		Assert.assertEquals(1, values.size());
		Assert.assertEquals("5", values.get(0));

		values = reader.readLine();
		Assert.assertEquals(2, values.size());
		Assert.assertEquals("value", values.get(0));
		Assert.assertEquals("otherValue", values.get(1));

		values = reader.readLine();
		Assert.assertEquals(2, values.size());
		Assert.assertEquals("Year", values.get(0));
		Assert.assertEquals("2012", values.get(1));

		Assert.assertEquals(null, reader.readLine());
	}
}
