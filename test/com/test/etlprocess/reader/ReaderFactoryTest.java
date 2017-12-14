package com.test.etlprocess.reader;

import org.junit.Assert;
import org.junit.Test;

public class ReaderFactoryTest {

	@Test
	public void testGetReader() {
		Assert.assertNotNull(ReaderFactory.getInstance().getReader(ReaderFactory.FILE_READER));
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetReaderWithInvalidReaderType() {
		ReaderFactory.getInstance().getReader(2);
	}
}
