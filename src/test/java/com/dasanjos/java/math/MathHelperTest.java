package com.dasanjos.java.math;

import org.junit.Assert;
import org.junit.Test;

public class MathHelperTest {

	@Test
	public void multiplicationTable() {
		String results = MathHelper.multiplicationTable(5);

		String result[] = results.split("\\n");
		Assert.assertEquals(5, result.length);
		Assert.assertEquals("   1   2   3   4   5", result[0]);
		Assert.assertEquals("   2   4   6   8  10", result[1]);
		Assert.assertEquals("   3   6   9  12  15", result[2]);
		Assert.assertEquals("   4   8  12  16  20", result[3]);
		Assert.assertEquals("   5  10  15  20  25", result[4]);
	}

}
