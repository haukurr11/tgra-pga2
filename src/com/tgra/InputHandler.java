package com.tgra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
	
	private String line = null;
	private List<String[]> rows;
	private String[] row;
	
	public InputHandler(String inputFile) {
		try	{
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			this.rows = new ArrayList<String[]>();
			try {
				while((line = in.readLine()) != null) {
					this.row = line.split(",");
					this.rows.add(row);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}	
		}
		catch (FileNotFoundException e)	{
			e.printStackTrace();
		} 
	}
	
	public List<String[]> getRowList() {
		return this.rows;
	}
}
