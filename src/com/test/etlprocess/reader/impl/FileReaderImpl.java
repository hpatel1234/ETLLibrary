package com.test.etlprocess.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.test.etlprocess.reader.Reader;

public class FileReaderImpl extends Reader {
	private List<File> files = new ArrayList<File>();

	@Override
	public String[] read() {
		List<String> content = null;
		BufferedReader buffer = null;
		if (files.size() > 0) {
			content = new ArrayList<String>();
			try {
				buffer = new BufferedReader(new FileReader(files.get(0)));
				while (true) {
					String line = buffer.readLine();
					if (line != null) {
						content.add(line);
					} else {
						files.remove(0);
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error reading provided source "
						+ files.get(0).getName());
			} finally {
				if (buffer != null) {
					try {
						buffer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (content != null && content.size() > 0) {
			return content.toArray(new String[content.size()]);
		}
		return null;
	}

	/**
	 * Initialise File reader by iterating through all files present in source
	 * directory and storing them in collection. Each time read method is
	 * called, it will read one file from collection.
	 */
	@Override
	public boolean initialise() {
		if (null == getSource()) {
			throw new RuntimeException("Source is not provided.");
		}
		try {
			File readDirectory = new File(getSource());
			if (readDirectory.isDirectory()) {
				File[] contents = readDirectory.listFiles();

				if (contents != null && contents.length > 0) {
					for (File file : contents) {
						if (file.getName().endsWith(".txt"))
						files.add(file);
					}
					Collections.sort(files, new Comparator<File>() {

						@Override
						public int compare(File o1, File o2) {
							return o1.getName().compareTo(o2.getName());
						}
					});
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
