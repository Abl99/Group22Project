package application;

import java.util.ArrayList;
import java.util.Arrays;
//UPDATED

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
			
		}else if (element_to_check.length() > 3) {
			//will assume this is an element
			
			//lower case String
			element_to_check = element_to_check.toLowerCase();
			
			System.out.println(element_to_check);
			
			//Look for the element
			for (int x = 0; x < all_elements.size();x ++) {
				
				//This should only look at even indexes (in which the elements are)
				if ((element_to_check.equals(all_elements.get(x))) && (x % 2 == 0)) {
					
					//element needs to be upper cased for JSON lookup
					return element_to_check.substring(0,1).toUpperCase() + element_to_check.substring(1);
				}
			}	
		}
		return "invalid element";
	}
}