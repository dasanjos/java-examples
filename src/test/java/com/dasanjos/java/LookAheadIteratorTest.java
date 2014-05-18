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
		LookAheadIterator lai = new LookAheadIterator(iterator);
		Assert.assertEquals(1, lai.peek());
		Assert.assertEquals(1, lai.peek());
		Assert.assertEquals(1, lai.peek());
		Assert.assertEquals(1, lai.next());
		Assert.assertEquals(2, lai.next());
		Assert.assertEquals(3, lai.peek());
		Assert.assertEquals(true, lai.hasNext());
		Assert.assertEquals(3, lai.next());
		Assert.assertEquals(false, lai.hasNext());
	}

}
