package com.dasanjos.java.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class PropertyTest {

	@Test
	public void testEquals() {
		assertEquals(new Property("key", "value"), new Property("key", "value"));
		assertNotSame(new Property("key", "value"), new Property("value", "key"));
		assertNotSame(new Property("key", "value"), null);
		assertNotSame(null, new Property("key", "value"));
	}

}
