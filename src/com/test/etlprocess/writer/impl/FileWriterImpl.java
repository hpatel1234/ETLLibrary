package com.test.etlprocess.writer.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.etlprocess.writer.Writer;

public class FileWriterImpl extends Writer {
	private List<String> outputfiles = new ArrayList<String>();

	/**
	 * this method will write content into first file from outputfiles.
	 */
	@Override
	public void write(String[] content) {
		if (null == getDestination()) {
			throw new RuntimeException("Destination is not provided.");
		}
		BufferedWriter buff = null;
		if (outputfiles.size() > 0) {
			try {
				File destFile = new File(getDestination() + "\\"
						+ outputfiles.get(0));
				destFile.createNewFile();
				buff = new BufferedWriter(new FileWriter(destFile));
				if (content != null && content.length > 0) {
					for (int lineNumber = 0; lineNumber < content.length; lineNumber++) {
						buff.write(content[lineNumber]);
						buff.newLine();
					}
				}
				outputfiles.remove(0);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(
						"Error writing to provided destination "
								+ outputfiles.get(0));
			} finally {
				if (buff != null) {
					try {
						buff.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Initialise File writer by iterating through all files present in source
	 * directory and store corresponding target/output file names in
	 * collections..
	 */
	@Override
	protected boolean initialise() {
		
		if (null == getSource()) {
			throw new RuntimeException("Source is not provided.");
		}
		try {
			File readDirectory = new File(getSource());
			if (readDirectory.isDirectory()) {
				File[] contents = readDirectory.listFiles();

				if (contents != null && contents.length > 0) {
					for (File file : contents) {
						if (file.getName().endsWith(".txt")) {
							outputfiles.add(file.getName().substring(0,
									file.getName().lastIndexOf('.') + 1)
									+ "out");
						}
						
					}
					Collections.sort(outputfiles);
				}
				return true;
			} else {
				System.out.println("Source provided is not valid directory.");
				return false;
			}
		} catch (Exception exception) {
			throw new RuntimeException("Error reading provided source.");
		}

	}

}
