package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//UPDATED

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//For the controller class, I am trying to check inputs for validity
//If this belongs in another class, then we can move it

//I am thinking this class should only be used for the elements section 

public class Valid_Input{
	
	private ArrayList<String> all_elements = new ArrayList<>(Arrays.asList(
    "hydrogen",
    "H",
    "helium",
    "He",
    "lithium","Li",
    "beryllium","Be",
    "boron","B",
    "carbon","C",
    "nitrogen","N",
    "oxygen","O",
    "fluorine","F",
    "neon","Ne",
    "sodium","Na",
    "magnesium","Mg",
    "aluminium","Al",
    "silicon","Si",
    "phosphorus","P",
    "sulfur","S",
    "chlorine","Cl",
    "argon","Ar",
    "potassium","K",
    "calcium","Ca",
    "scandium","Sc",
    "titanium","Ti",
    "vanadium","V",
    "chromium","Cr",
    "manganese","Mn",
    "iron","Fe",
    "cobalt","Co",
    "nickel","Ni",
    "copper","Cu",
    "zinc","Zn",
    "gallium","Ga",
    "germanium","Ge",
    "arsenic","As",
    "selenium","Se",
    "bromine","Br",
    "krypton","Kr",
    "rubidium","Rb",
    "strontium","Sr",
    "yttrium","Y",
    "zirconium","Zr",
    "niobium","Nb",
    "molybdenum","Mo",
    "technetium","Tc",
    "ruthenium","Ru",
    "rhodium","Rh",
    "palladium","Pd",
    "silver","Ag",
    "cadmium","Cd",
    "indium","In",
    "tin","Sn",
    "antimony","Sb",
    "tellurium","Te",
    "iodine","I",
    "xenon","Xe",
    "cesium","Cs",
    "barium","Ba",
    "lanthanum","La",
    "cerium","Ce",
    "praseodymium","Pr",
    "neodymium","Nd",
    "promethium","Pm",
    "samarium","Sm",
    "europium","Eu",
    "gadolinium","Gd",
    "terbium","Tb",
    "dysprosium","Dy",
    "holmium","Ho",
    "erbium","Er",
    "thulium","Tm",
    "ytterbium","Yb",
    "lutetium","Lu",
    "hafnium","Hf",
    "tantalum","Ta",
    "tungsten","W",
    "rhenium","Re",
    "osmium","Os",
    "iridium","Ir",
    "platinum","Pt",
    "gold","Au",
    "mercury","Hg",
    "thallium","Tl",
    "lead","Pb",
    "bismuth","Bi",
    "polonium","Po",
    "astatine","At",
    "radon","Rn",
    "francium","Fr",
    "radium","Ra",
    "actinium","Ac",
    "thorium","Th",
    "protactinium","Pa",
    "uranium","U",
    "neptunium","Np",
    "plutonium","Pu",
    "americium","Am",
    "curium","Cm",
    "berkelium","Bk",
    "californium","Cf",
    "einsteinium","Es",
    "fermium","Fm",
    "mendelevium","Md",
    "nobelium","No",
    "lawrencium","Lr",
    "rutherfordium","Rf",
    "dubnium","Db",
    "seaborgium","Sg",
    "bohrium","Bh",
    "hassium","Hs",
    "meitnerium","Mt",
    "darmstadtium","Ds",
    "roentgenium","Rg",
    "copernicium","Cn",
    "nihonium","Nh",
    "flerovium","Fl",
    "moscovium","Mc",
    "livermorium","Lv",
    "tennessine","Ts",
    "oganesson","Og",
    "ununennium", "Uue"));
	
	protected String Test(String element_to_check) {
		
		//will assume it is not digit 
		if (element_to_check.equals(null) || element_to_check.equals("")){
			return "invalid element";
		}else {
			//remove any whitespace
			element_to_check = element_to_check.trim();
		}
		
		//Check as a symbol
		if (element_to_check.length() < 3) {
			
			//upper case the string
			element_to_check = element_to_check.substring(0,1).toUpperCase() + element_to_check.substring(1).toLowerCase();
			
			//Look for the element symbol
			for (int x = 0; x < all_elements.size();x ++) {
				
				//This should only look at odd indexes (in which the symbols are)
				if ((element_to_check.equals(all_elements.get(x))) && !(x % 2 == 0)) {
					return element_to_check;
				}
			}
		
		//Check as an element
		}else if (element_to_check.length() > 3) {
			
			//lower case String so as to compare to other lower cased elements in array list
			element_to_check = element_to_check.toLowerCase();
			
			//Look for the element
			for (int x = 0; x < all_elements.size();x ++) {
				
				//This should only look at even indexes (in which the elements are)
				if ((element_to_check.equals(all_elements.get(x))) && (x % 2 == 0)) {
					
					//element needs to be upper cased for JSON lookup (if possible)
					return element_to_check.substring(0,1).toUpperCase() + element_to_check.substring(1);
				}
			}	
		}
		return "invalid element";
	}
	
	
	protected String getElement(String symbol) {
		
		if(symbol.length() > 3) {
			return "invalid symbol";
		}
		
		if (Test(symbol).equals("invalid element")) {
			return "invalid element";
		}
		
		//upper case symbol for array list search 
		symbol = symbol.substring(0,1).toUpperCase() + symbol.substring(1).toLowerCase();
		
		//Look for the element symbol
		for (int x = 0; x < all_elements.size();x ++) {
			
			//This should only look at odd indexes (in which the symbols are)
			if ((symbol.equals(all_elements.get(x))) && !(x % 2 == 0)) {
				return all_elements.get(x-1);
			}
		}
		
		//This should not be returned
		return "ERROR IN GET ELEMENT";
	}
	
	
	protected String getSymbol(String element) {
	
		if (Test(element).equals("invalid element") || element.length() < 3) {
			return "invalid element";
		}
		
		//lower case for element search 
		element = element.toLowerCase();
		
		//Look for the element symbol
		for (int x = 0; x < all_elements.size();x ++) {
			
			//This should only look at odd indexes (in which the symbols are)
			if ((element.equals(all_elements.get(x))) && (x % 2 == 0)) {
				return all_elements.get(x+1);
			}
		}
		
		//This should not be returned
		return "ERROR IN GET SYMBOL";
	}
	
	
	protected double getAtomicWeight(String value) {          //WORK IN PROGRESS
		
		//needs to be valid element/symbol
		if (Test(value).equals("invalid element")) {
			return 0;
			
		}else if (value.length() > 3){ //If an element was value
			value.toLowerCase();
			
		
		}else if (value.length() < 3) { //If a symbol was entered, give me the element of it
			value = getElement(value);
		}		
		
		//At this point, I will have a lower case element which can be used in the periodic-table-lookup.json file
		
		//-------------------------------------------------------------------------------------
		//JSON ATOMIC WEIGHT LOOKUP (WIP)
		
		JSONParser jsonparser = new JSONParser();
		
		
		//BORROWED CODE FROM ATOM, WILL BE ALTERING IT FOR PURPOSES
		/*
		try {
			FileReader reader = new FileReader("src/application/periodic-table-lookup-modified.json");		
			Object obj = jsonparser.parse(reader);
			JSONObject jsonObj = (JSONObject) obj;
			Object value =  jsonObj.get(key);
			
			
			
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
		*/

		return 0;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}