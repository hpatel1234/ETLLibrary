package com.test.etlprocess.reader;

import com.test.etlprocess.reader.impl.FileReaderImpl;
/**
 * This is factory class for Reader classes. To add new Reader change getReader method will be needed.
 *
 */
public class ReaderFactory {
    private static ReaderFactory instance = new ReaderFactory();
	private ReaderFactory() {
	}
	
	public static ReaderFactory getInstance() {
		return instance;
	}
	
	public Reader getReader(int readerType) {
		switch(readerType) {
			case 1: return new FileReaderImpl();
			default : throw new RuntimeException("Requested reader is not supported.");
		}
	}
}
