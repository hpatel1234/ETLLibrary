package com.test.etlprocess.formatter;
/**
 * This is base formatter class. Each new Formatter should extend this class, and just override format method with String input.
 * This will let Author of new Formatter to concentrate on only his formatting logic.
 *
 */
public abstract class Formatter {
	private Formatter next;
	private String input;

	public final String format() {
		if (input == null) {
			return "";
		}
		String output = format(input);
		if (next != null) {
			next.setInput(output);
			return next.format();
		}
		return output;
	}
	
	protected abstract String format(String input);

	public void setNext(Formatter next) {
		this.next = next;
	}

	protected String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}
