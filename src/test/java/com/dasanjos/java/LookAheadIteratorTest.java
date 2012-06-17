package com.dasanjos.java;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class LookAheadIteratorTest {

	@Test
	public void testInitialCase() throws Exception {
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(1);
		values.add(2);
		values.add(3);

		Iterator<Integer> iterator = values.iterator();
		LookAheadIterator ila = new LookAheadIterator(iterator);
		Assert.assertEquals(1, ila.peek());
		Assert.assertEquals(1, ila.peek());
		Assert.assertEquals(1, ila.peek());
		Assert.assertEquals(1, ila.next());
		Assert.assertEquals(2, ila.next());
		Assert.assertEquals(3, ila.peek());
		Assert.assertEquals(true, ila.hasNext());
		Assert.assertEquals(3, ila.next());
		Assert.assertEquals(false, ila.hasNext());
	}

}
