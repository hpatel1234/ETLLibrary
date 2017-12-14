package com.test.etlprocess.writer;

import com.test.etlprocess.writer.impl.FileWriterImpl;
/**
 * This is factory class for Reader classes. To add new Reader change getWriter method will be needed.
 *
 */
public class WriterFactory {
	public static final int FILLE_WRITER = 1;
	private static WriterFactory instance = new WriterFactory();
	private WriterFactory() {
	}
	
	public static WriterFactory getInstance() {
		return instance;
	}
	
	public Writer getWriter(int writerType) {
		switch(writerType) {
			case 1: return new FileWriterImpl();
			default : throw new RuntimeException("Requested writer is not supported.");
		}
	}
}
