package com.test.etlprocess.reader;
/**
 * This is base Reader class. Each new Reader should extend this class, and just override read and initialise method.
 *
 */ 
public abstract class Reader {
	private String source;

	protected String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
		initialise();
	}

	public abstract String[] read();
	protected abstract boolean initialise();
}
