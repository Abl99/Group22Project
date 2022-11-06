package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Atom {
	String name;
	String symbol;
	
	Atom (){}
	
	Atom (String i){
		//TODO need a way to tell if 'i' is the name or the symbol,
		// and then need to fetch the missing piece from the element data file
		
		name = i;
		symbol = i;
	}
	
	double getAtomicWeight() {
		// TODO add a method to fetch the atomic weight from the data file
		
		
		
		try {
			Scanner reader = new Scanner(new File("src/application/periodic-table-lookup.json"));
			String line = reader.nextLine();
			System.out.println(line);
			
			//JSONParser jsonParser = new JSONParser();
			
			reader.close();
		
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("threw an exception");
			e.printStackTrace();
		}
		
		
		return 0.0;
		
	}
	
	public static void main(String[] args) {
		Atom test = new Atom("H");
		test.getAtomicWeight();
	}

}
