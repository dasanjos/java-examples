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

	@Test
	public void oddNumbers() {
		String result = MathHelper.oddNumbers(20);
		Assert.assertEquals("  1  3  5  7  9 11 13 15 17 19", result);
	}

	@Test
	public void findLargest() {
		int[] input = { 0, 4, 7, 9, 1, 14, -1, 0, 2 };
		Assert.assertEquals(14, MathHelper.findLargest(input));
	}

	@Test
	public void rgbToHex() {
		Assert.assertEquals("000000", MathHelper.rgbToHex(0, 0, 0));
		Assert.assertEquals("0F0F0F", MathHelper.rgbToHex(15, 15, 15));
		Assert.assertEquals("123456", MathHelper.rgbToHex(18, 52, 86));
		Assert.assertEquals("567890", MathHelper.rgbToHex(86, 120, 144));
		Assert.assertEquals("ABCDEF", MathHelper.rgbToHex(171, 205, 239));
		Assert.assertEquals("CCCCCC", MathHelper.rgbToHex(204, 204, 204));
		Assert.assertEquals("FFFFFF", MathHelper.rgbToHex(255, 255, 255));
	}

	@Test
	public void hexToRgb() {
		Assert.assertEquals("  0,   0,   0", MathHelper.hexToRgb("000000"));
		Assert.assertEquals(" 15,  15,  15", MathHelper.hexToRgb("0F0F0F"));
		Assert.assertEquals(" 18,  52,  86", MathHelper.hexToRgb("123456"));
		Assert.assertEquals(" 86, 120, 144", MathHelper.hexToRgb("567890"));
		Assert.assertEquals("171, 205, 239", MathHelper.hexToRgb("ABCDEF"));
		Assert.assertEquals("204, 204, 204", MathHelper.hexToRgb("CCCCCC"));
		Assert.assertEquals("255, 255, 255", MathHelper.hexToRgb("FFFFFF"));
	}
}
