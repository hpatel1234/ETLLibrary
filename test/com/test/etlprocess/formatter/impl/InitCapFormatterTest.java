package com.test.etlprocess.formatter.impl;


import org.junit.Assert;
import org.junit.Test;

import com.test.etlprocess.formatter.Formatter;

public class InitCapFormatterTest {

	private InitCapFormatter testInstance = new InitCapFormatter();
	
	@Test
	public void testFormat() {
		testInstance.setInput("i am author");
		String output = testInstance.format();
		Assert.assertEquals("I Am Author", output);
	}
	
	@Test
	public void testFormatWithNullInput() {
		String output = testInstance.format();
		Assert.assertEquals("", output);
	}
	
	@Test
	public void testFormatWithEmptyInput() {
		testInstance.setInput("   ");
		String output = testInstance.format();
		Assert.assertEquals("", output);
	}
	
	//This test case is written to demonstrate additional formatter 
	@Test
	public void testFormatWithAdditionalFormatter() {
		//Formatter to bold String input
		Formatter additional = new Formatter() {
			@Override
			protected String format(String input) {
				return "<bold>"+input+"</bold>";
			}
		};
		testInstance.setNext(additional);
		testInstance.setInput("i am author");
		String output = testInstance.format();
		Assert.assertEquals("<bold>I Am Author</bold>", output);
	}
}
