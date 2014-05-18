package com.dasanjos.java;

import org.junit.Assert;
import org.junit.Test;

import com.dasanjos.java.dataStructure.Hashtable;

public class HashTableTest {

	@Test
	public void addElements() {
		Hashtable ht = new Hashtable(10);
		ht.add("key1", "value1");
		ht.add("key2", "value2");
		ht.add("key3", "value3");
		Assert.assertTrue(ht.contains("key2"));
		System.out.println(ht.toString());
	}

}
