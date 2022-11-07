package application;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

//For the controller class, I am trying to check inputs for validity
//If this belongs in another class, then we can move it

//I am thinking this class should only be used for the elements section 

public class Valid_Input{
	String message;
	

	public Valid_Input(String element_to_check){
		
		if (element_to_check.isBlank() || element_to_check.isEmpty() || element_to_check == null) {
			message = "No null values";
		
		}else {
			
			//upper case element_to_check
			element_to_check = element_to_check.substring(0,1).toUpperCase() + element_to_check.substring(1).toLowerCase();
			
			
			
			//I AM USING THE LINK BELOW IN ORDER TO GET JSON TO WORK
			//https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
			
			//Learning how to read JSON Files
			//PeriodicTableJSON.json
			
			JSONParser testing = new JSONParser();
			
			try {
			
			Object testing2 = testing.parse(new FileReader("src/application/PeriodicTableJSON.json"));
			JSONObject testing3 = (JSONObject) testing2;
			
			
			String element = "No element";
			//Will need to test if test_me is an element
			//if it isn't in the json file, would it try to return null?
			
			if (!testing3.get(element_to_check).equals(null)) {
				element = (String) testing3.get(element_to_check);
			}
			//System.out.println(element);
			
			
			
			}catch(FileNotFoundException e) {
				e.getMessage();
			}catch(ParseException e) {
				e.getMessage();
			}catch(IOException e) {
				e.getMessage();
			}	
		}
	}
}
