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

public class Atom{
	
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
	
	
	/**
	 * @param element_to_check: Checks if a given string is a valid element or not (uses all_elements).
	 * @return the string "invalid element" if the string parameter is not an element. If it is an element, it will return
	 * the element that it is in the form it was given (put capitalized). 
	 */
	
	protected String Test(String element_to_check) {
		
		//will assume it is not digit 
		if (element_to_check.equals(null) || element_to_check.equals("")){
			return "invalid element";
		}else {
			//remove any whitespace
			element_to_check = element_to_check.trim();
		}
		
		//Check as a symbol
		if (element_to_check.length() <= 3) {
			
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
				if ((element_to_check.equals(all_elements.get(x)))) {
					
					//element needs to be upper cased for JSON lookup (if possible)
					return element_to_check.substring(0,1).toUpperCase() + element_to_check.substring(1);
				}
			}	
		}
		return "invalid element";
	}
	
	/**
	 * 
	 * @param symbol: takes an element symbol and returns it's name. If element name already, just returns itself. 
	 * @return string: the name of the element. 
	 */
	
	protected String getElement(String symbol) {
		
		if(symbol.length() > 3) {
			symbol.toLowerCase();
			return symbol;
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
	
	/**
	 * 
	 * @param element: takes the element name, and gives the symbol. If a symbol already, just returns itself. 
	 * @return string: element in symbol form. 
	 */
	
	protected String getSymbol(String element) {
	
		if (Test(element).equals("invalid element") || element.length() <= 3) {
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
	
	/**
	 * 
	 * @param value: an element or a symbol (will be converted to element regardless). 
	 * @param selected_trait: searches the JSON document for specific information on that element and returns it as a string. 
	 * @return string: the specific element information that parameter requests (i.e. atomic "number" is 2). 
	 */
	
	private String get_Element_Traits (String value, String selected_trait) {
		//needs to be valid element/symbol
		if (Test(value).equals("invalid element")) {
			return null;
			
		}else if (value.length() > 3){ //If an element was value
			value = value.toLowerCase();
			
		
		}else if (value.length() < 3) { //If a symbol was entered, give me the element of it
			value = getElement(value);
		}	
		
		JSONParser jsonparser = new JSONParser();
		
		try {
			FileReader reader = new FileReader("src/application/periodic-table-lookup-modified.json");
			JSONObject obj = (JSONObject) jsonparser.parse(reader);
			JSONObject element_traits =  (JSONObject) obj.get(value);
			
			//Since is instance doesn't really exist in java
			//I just try to figure out what type of object element_traits will be
			//If its a double, I convert it to string, etc.
			
			try {
				return (String) element_traits.get(selected_trait);
			
			}catch (ClassCastException x) {
				
				try {
					return Double.toString( (double) element_traits.get(selected_trait));
				
				}catch (ClassCastException y) {
					
					return Long.toString((long) element_traits.get(selected_trait));
				}
			}

		}catch(FileNotFoundException e) {
			e.getMessage();
		}catch(ParseException e) {
			e.getMessage();
		}catch(IOException e) {
			e.getMessage();
		}
		
		return null;
	}
	
	//It makes sense to have these all return a string, since labels only accept strings
	
	protected String getAtomicWeight(String element) {
		if (Test(element).equals("invalid element")) {
			return "invalid element";
		}
		return get_Element_Traits(element, "atomic_mass");
	}
	
	protected String getAtomicNumber(String element) {
		if (Test(element).equals("invalid element")) {
			return "invalid element";
		}
		return get_Element_Traits(element, "number");
	}
	
	protected String getCategory(String element) {
		if (Test(element).equals("invalid element")) {
			return "invalid element";
		}
		return get_Element_Traits(element, "category");
	}
	
	protected String getBoil(String element) {
		if (Test(element).equals("invalid element")) {
			return "invalid element";
		}
		return  get_Element_Traits(element, "boil");
	}
	
	protected String getDensity (String element) {
		if (Test(element).equals("invalid element")) {
			return "invalid element";
		}
		return get_Element_Traits(element, "density");
	}
	
	protected String getPhase(String element) {
		if (Test(element).equals("invalid element")) {
			return "invalid element";
		}
		return (String) get_Element_Traits(element, "phase");
	}
	
}