package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Atom {
	private String name;
	private String symbol;
	
	public Atom (){
		setName("");
		setSymbol("");
	}
	public Atom (String i){
		if (elementExistence(i)) {
			if (i.length()>3) {
				setName(i);
				setSymbol(fetchNameOrSymbol(i));
			}else {
				setName(fetchNameOrSymbol(i));
				setSymbol(i);
			}
		}else {
			setName("");
			setSymbol("");
		}
	}
	
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	String getSymbol() {
		return symbol;
	}
	void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * This method accesses the element data file ("src/application/periodic-table-lookup-modified.json")
	 * and uses the key provided to fetch the corresponding value.
	 * @param key: is the key corresponding to the wanted information from the element data file.
	 * @return the value fetched from the element data file as an Object.
	 */
	private Object fileLookup(String key) {
		JSONParser jsonparser = new JSONParser();
		try {
			FileReader reader = new FileReader("src/application/periodic-table-lookup-modified.json");		
			Object obj = jsonparser.parse(reader);
			JSONObject jsonObj = (JSONObject) obj;
			Object value =  jsonObj.get(key);
			return value;
		//TODO put useful catch statements
		}catch(FileNotFoundException e) {
			e.getMessage();
			System.out.println("FNF exception");
		}catch(ParseException e) {
			e.getMessage();
			System.out.println("Parse Exception");
		}catch(IOException e) {
			e.getMessage();
			System.out.println("IO Exception");
		}
		return null;
	}
	/**
	 * This method finds the atomic weight for the instance of Atom that it is called on. If the instance
	 * variable 'name' does not exist (for example, if it is currently blank), then 0.0 is returned.
	 * @return the atomic weight of the instance of Atom, or 0.0 if the instance name variable is not
	 * an element that exists (aka. is "" or null).
	 */
	protected double getAtomicWeight() {
		// TODO add a method to fetch the atomic weight from the data file
		if (elementExistence(this.name)) {
			String element = (this.name).toLowerCase();
			JSONObject atom = (JSONObject) fileLookup(element);
			double atomicWeight = 0.0;			
			atomicWeight = (double) atom.get("atomic_mass");	
			return atomicWeight;
		}else return 0.0;
	}
	
	// may need to move this class to the Validating class
	protected boolean elementExistence (String element) {
		if (element.length() > 3) element = element.toLowerCase();		
		JSONArray elementList = (JSONArray) fileLookup("order");
		for (int index = 0; index < elementList.size(); index ++) {
			if (element.equals(elementList.get(index))) return true;
		}
		return false;
	}
	/**
	 * will return the element symbol if given the name, and return the name if given the symbol
	 * @param element is the name or symbol
	 * @return is the symbol or name. if there was an error, a blank string is returned
	 */
	protected String fetchNameOrSymbol (String element) {
		String nameOrSymbol = "";
		if (element.length() > 3) element = element.toLowerCase();		
		if (!(element == null || element.equals(""))){			
			JSONArray atom = (JSONArray) fileLookup("order");
			if (element.length()>3) {
				int index = atom.indexOf(element);
				nameOrSymbol = "" + atom.get(index+1);
			}else {
				int index = atom.indexOf(element);
				nameOrSymbol = "" + atom.get(index-1);
			}
		}	
		return nameOrSymbol;
	}
	
	

}
