package com.tgra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
	private List<Wall> walls;
	private List<Spongebob> spongebobs;

	public InputHandler(String inputFile) {
		
		try	{
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			this.walls = new ArrayList<Wall>();
			this.spongebobs = new ArrayList<Spongebob>();
			try {
				String line;
				while((line = in.readLine()) != null) {
					String[] row = line.split(",");
					if(!row[2].equals("2")) {
					this.walls.add( 
						new Wall(
								Integer.parseInt(row[0]),
								Integer.parseInt(row[1]),row[2].equals("1")));
					}
					else {
						this.spongebobs.add(new Spongebob(Integer.parseInt(row[0]),
														  Integer.parseInt(row[1])
								));
					}
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
	
	public List<Wall> getWalls() {
		return this.walls;
	}
	public List<Spongebob> getSpongebobs() {
		return this.spongebobs;
	}
}
