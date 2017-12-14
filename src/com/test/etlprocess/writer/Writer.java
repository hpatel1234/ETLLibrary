package com.test.etlprocess.writer;
/**
 * This is base Writer class. Each new Writer should extend this class, and just override write and initialise method.
 *
 */
public abstract class Writer {
	private String destination;
	private String source;
	
	protected String getDestination() {
		return destination;
	}
	protected String getSource() {
		return source;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
    public void setSource(String source) {
		this.source = source;
		initialise();
	}
	public abstract void write(String[] content);
	protected abstract boolean initialise();
}
