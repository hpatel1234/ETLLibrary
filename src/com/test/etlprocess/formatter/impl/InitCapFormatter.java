package com.test.etlprocess.formatter.impl;

import com.test.etlprocess.formatter.Formatter;
/**
 * This formatter will convert input provided to it into Init Cap format.
 *
 */
public class InitCapFormatter extends Formatter {

	@Override
	protected String format(String input) {
		if (input != null && input.trim().length() > 0) {
            char[] charArray = input.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            for (int index = 1; index < charArray.length; index++) {
            	if(charArray[index -1] == ' ') {
            		charArray[index] = Character.toUpperCase(charArray[index]);
            	}
            }
            return new String(charArray);
        } else {
            return "";
        }
	}

}
