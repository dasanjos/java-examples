package com.dasanjos.java.dataStructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dasanjos.java.dataStructure.HashTable;

public class HashTableTest {

	HashTable ht;

	@Before
	public void setup() {
		ht = new HashTable(9);
	}

	@Test
	public void addElements() {
		ht.insert("key1", "value1");
		ht.insert("key2", "value2");
		ht.insert("key3", "value3");

		Assert.assertEquals(ht.size(), 3);
	}

	@Test
	public void searchElements() {
		ht.insert("key1", "value1");
		ht.insert("key2", "value2");
		ht.insert("key3", "value3");

		Assert.assertNotNull(ht.search("key1"));
		Assert.assertNotNull(ht.search("key2"));
		Assert.assertNotNull(ht.search("key3"));
		Assert.assertNull(ht.search("key4"));
	}

	@Test(expected = RuntimeException.class)
	public void addTooManyElements() {
		ht.insert("key1", "value1");
		ht.insert("key2", "value2");
		ht.insert("key3", "value3");
		ht.insert("key4", "value4");
		ht.insert("key5", "value5");
		ht.insert("key6", "value6");
		ht.insert("key7", "value7");
		ht.insert("key8", "value8");
		ht.insert("key9", "value9");
		Assert.assertEquals(ht.size(), 9);
		System.out.println(ht.toString());
		ht.insert("key10", "value10");
	}

	@Test
	public void removeElements() {
		ht.insert("key1", "value1");
		ht.insert("key2", "value2");

		ht.insert("key3", "value3");
		Assert.assertNotNull(ht.search("key3"));
		Assert.assertEquals(ht.size(), 3);

		ht.delete("key3");
		Assert.assertNull(ht.search("key3"));
		Assert.assertEquals(ht.size(), 2);
	}

	@Test
	public void removeAllElements() {
		ht.insert("key1", "value1");
		ht.insert("key2", "value2");
		ht.insert("key3", "value3");
		Assert.assertNotNull(ht.search("key3"));
		Assert.assertEquals(ht.size(), 3);

		ht.delete("key3");
		Assert.assertNull(ht.search("key3"));
		Assert.assertEquals(ht.size(), 2);

		ht.delete("key2");
		Assert.assertNull(ht.search("key2"));
		Assert.assertEquals(ht.size(), 1);

		ht.delete("key1");
		Assert.assertNull(ht.search("key1"));
		Assert.assertEquals(ht.size(), 0);
	}

}
