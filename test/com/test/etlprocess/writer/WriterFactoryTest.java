package com.test.etlprocess.writer;

import org.junit.Assert;
import org.junit.Test;

public class WriterFactoryTest {

	@Test
	public void testGetWriter() {
		Assert.assertNotNull(WriterFactory.getInstance().getWriter(WriterFactory.FILLE_WRITER));
	}
	
	@Test(expected = RuntimeException.class)
	public void testGetWriterWithInvalidWriterType() {
		WriterFactory.getInstance().getWriter(WriterFactory.FILLE_WRITER);
	}
}
