package com.test.etlprocess.reader.impl;

import org.junit.Assert;
import org.junit.Test;

public class FileReaderImpltest {
	private FileReaderImpl testInstance = new FileReaderImpl();
	
	@Test
	public void testRead() {
		testInstance.setSource(".");
		String[] contents = testInstance.read();
		Assert.assertEquals("i am author", contents[0]);
	}
	
	@Test(expected = RuntimeException.class)
	public void testReadWithNullsource() {
		testInstance.setSource(null);
		testInstance.read();
	}
	
	@Test
	public void testReadWithEmptysource() {
		testInstance.setSource(" ");
		Assert.assertNull(testInstance.read());
	}
}
