package application;

import java.util.Arrays;

public class Valid_Molecules extends Valid_Atoms{

	//ArrayList<String> molecule_input = new ArrayList<String>();
	
	private String[] atom_numbers;
	private String[] atoms;
	
	public Valid_Molecules(String molecule) {
		
		//get numbers (if any)
		String numbers = molecule.replaceAll("[^0-9]", "");
	
		//lower case molecule and replace any numbers with blank
		molecule = molecule.toLowerCase().replaceAll("[^a-z]", " ");
		
		//Assign the atoms and their numbers to the corresponding string arrays
		atom_numbers = numbers.split("");
		atoms = molecule.split(" ");
	}
	
	
	protected String getMoleculeWeight() {
		
		//Needs to return string since labels can only show strings
		double molecule_weight = 0;
		
		for (int x = 0; x < atoms.length;x++) {
			Valid_Atoms test_me = new Valid_Atoms();
			String to_test = atoms[x];
			
			if (test_me.Test(to_test).equals("invalid element")) {
				return "invalid element";
			}else {
				
				double number = Double.parseDouble(atom_numbers[x]);
				double element = Double.parseDouble(test_me.getAtomicWeight(to_test));
				
				molecule_weight += number*element;
			}
		}
		return Double.toString(molecule_weight);
	}
	
	protected String getMoleculeName() {
		
		String molecule_name = "";
		
		for (int x = 0; x < atoms.length;x++) {
			Valid_Atoms test_me = new Valid_Atoms();
			String to_test = atoms[x];
			
			if (test_me.Test(to_test).equals("invalid element")) {
				return "invalid element";
			}else {

				//I need to get elements here
				molecule_name += atoms[x] + " " + atom_numbers[x] + " ";
			}
		}
		return molecule_name;
	}

}
