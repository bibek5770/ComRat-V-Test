package textFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWrite {
	private String textFileSourceRead = "Answers.txt";
	private String textFileSourceWrite = "LogFiles\\LogFile.txt";

	public void writeLog(String content) {
		try {

			File file = new File(this.textFileSourceWrite);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			//System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String[]> readQueries() {
		ArrayList<String[]> queries = new ArrayList<String[]>();
		try {
			FileReader inputFile = new FileReader(this.textFileSourceRead);
			BufferedReader buffy = new BufferedReader(inputFile);
			String line;
			String[] testLine;
			while ((line = buffy.readLine()) != null) {
				testLine = new String[4];
				Scanner scan = new Scanner(line);
				scan.useDelimiter("#");
				int w = 0;
				while (scan.hasNext()) {
					testLine[w] = scan.next().trim().toLowerCase();
					++w;
				}
				if (w == 4) {
					queries.add(testLine);
				}
				scan.close();
			}
			buffy.close();
		} catch (IOException e) {
			e.printStackTrace();
			/*
			 * System.out
			 * .println("There was an exception when retrieving the Query File: "
			 * + e.getMessage()); e.printStackTrace();
			 */
			return null;

		}
		return queries;
	}

	public synchronized String getReadTextFileSource() {
		return this.textFileSourceRead;
	}

	public synchronized void setReadTextFileSource(String textFileSource) {
		this.textFileSourceRead = textFileSource;
	}

	public String getWriteTextFileSource() {
		synchronized (this) {
			return this.textFileSourceWrite;
		}
	}

	public synchronized void setWriteTextFileSource(String textFileSource) {
		this.textFileSourceWrite = textFileSource;
	}

	
}
