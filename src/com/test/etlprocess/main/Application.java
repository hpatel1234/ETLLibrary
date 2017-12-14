package com.test.etlprocess.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.test.etlprocess.formatter.Formatter;
import com.test.etlprocess.formatter.impl.InitCapFormatter;
import com.test.etlprocess.reader.Reader;
import com.test.etlprocess.reader.ReaderFactory;
import com.test.etlprocess.writer.Writer;
import com.test.etlprocess.writer.WriterFactory;

/**
 * This is Main class.
 * 
 *
 */
public class Application {
	public static void main(String[] args) throws IOException {
		System.out.println("Application Execution Started");
		String sourceDir = null;
		String destDir = null;
		if (args.length != 2) {
			System.out.println("ERROR: Illegal Use. Usage: java Application sourcedirectory destdirectory");
			System.out.println("Defaulting source directory and Destination Directory to: C:\\test");
			System.out.println("Creating sample file: File1.txt and File2.txt");
			sourceDir = "c:\\test";
			destDir = sourceDir;
			prepareDefaultEnvironment(destDir);
		} else {
			sourceDir = args[0];
			destDir = args[1];
		}
		//Hard coded 1 is passed to get Reader which can read Files, when there are multiple readers this can be read from arguments list.
		Reader reader = ReaderFactory.getInstance().getReader(1);
		reader.setSource(sourceDir);
		//Hard coded 1 is passed to get writer wich can write to files, when there are multiple writers this can be read from arguments list.
		Writer writer = WriterFactory.getInstance().getWriter(1);
		writer.setSource(sourceDir);
		writer.setDestination(destDir);
		
		//Only one formatter initialised, as per requirement. If we need to have some more formatting to be done on input,
		//You can set the next formatter in chain by calling setNext on initial formatter.
		//eg. Formatter formatter = new InitCapFormatter();
		//    formatter.setNext(new BoldFormatter());
		// In this way content will be first converted to Init cap and then to bold
		Formatter formatter = new InitCapFormatter();
		while (true) {
			String[] content = reader.read();
			if (content != null && content.length > 0) {
				for (int index = 0; index < content.length; index++) {
					formatter.setInput(content[index]);
					content[index] = formatter.format();
				}
				writer.write(content);
			} else {
				break;
			}
		}
		System.out.println("Application Execution Finished");
	}

	/**
	 * This method is specifically written to demonstrate application working.
	 * It creates directory C:\\test on client machine and write sample files to process inside that directory.
	 * @param destDir
	 * @throws IOException 
	 */
	private static void prepareDefaultEnvironment(String destDir) throws IOException {
		BufferedWriter writer = null;
		// Create Directory
		try {
			File directory = new File(destDir);
			directory.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Create Files
		try {

			writer = new BufferedWriter(new FileWriter(destDir + "\\File1.txt"));
			writer.write("I am a great coder who loves to solve real world problems");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {

			writer = new BufferedWriter(new FileWriter(destDir + "\\File2.txt"));
			writer.write("Design patterns helps me to write good modular and extensible code");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
