package application;

import java.util.ArrayList;

public class Molecule extends Atom{
	private String molecularFormula;
	
	public Molecule (){
		molecularFormula = "";
	}
	
	public Molecule (String m){
		molecularFormula = m;
	}
	
	String getMolecularFormula() {
		return molecularFormula;
	}
	void setMolecularFormula(String name) {
		this.molecularFormula = name;
	}
	
	/**
	 * takes the molecularFormula instance variable and splits it into an array that contains the atoms and 
	 * the number of that atom as individual elements
	 * @return an array that contains the elements at even indexes and the number of that element in the 
	 * following odd index
	 */
	public ArrayList<String> getMoleculeArray() {
		ArrayList<String> moleculeParts = new ArrayList<String>();
		for (int index = 0; index < molecularFormula.length(); index ++) {
			String atomAndNumber = Character.toString(molecularFormula.charAt(index));
			while (index+1 < molecularFormula.length() && !Character.isUpperCase(molecularFormula.charAt(index+1))) {
				atomAndNumber = atomAndNumber + molecularFormula.charAt(index+1);
				index ++;
			}
			moleculeParts.add(atomAndNumber);
		}
		ArrayList<String> moleculeArray = new ArrayList<String>();
		for (int element = 0; element < moleculeParts.size(); element ++) {
			String atomNumber = moleculeParts.get(element);
			String number = "";
			int index = atomNumber.length()-1;
			while (Character.isDigit(atomNumber.charAt(index)) && index >= 0) {
				number = atomNumber.charAt(index) + number;
				atomNumber = atomNumber.substring(0, index);
				index --;
			}
			if (number.equals("")) number ="1";
			String atom = atomNumber;
			moleculeArray.add(atom);
			moleculeArray.add(number);
		}
		return moleculeArray;
	}
	/**
	 * This method calculates the molecular weight of an instance of Molecule
	 * @return the molecular weight. If any elements in the molecular formula do not exist, their
	 * atomic weight is treated as 0.0, and the molecular weight is calculated as usual.
	 */
	public Double getMolecularWeight() {
		//TODO test method
		double sum = 0.0;
		ArrayList<String> moleculeArray = getMoleculeArray();
		for (int index = 0; index < moleculeArray.size(); index = index+2) {
			
			Atom x = new Atom();
			sum += Double.parseDouble(x.getAtomicWeight(moleculeArray.get(index))) * Integer.parseInt(moleculeArray.get(index+1));
		}
		return sum;
	}

	// must be given a proper array with atoms at even indexes and numbers at odd indexes
	protected ArrayList<String> condenseMolecule(ArrayList<String> fullMolecule){
		// terminating condition
		if (fullMolecule.size() <= 2) return fullMolecule;
		
		// recursive call
		ArrayList<String> partiallyCondensed = condenseMolecule(new ArrayList<String>(fullMolecule.subList(2,fullMolecule.size())));
		
		// use results of recursive call		
		int index = partiallyCondensed.indexOf(fullMolecule.get(0));
		if (index >= 0) {
			int newCoeff = (Integer.parseInt(partiallyCondensed.get(index+1))) + (Integer.parseInt(fullMolecule.get(1)));
			String newcoeff = Integer.toString(newCoeff);
			partiallyCondensed.set(index+1, newcoeff);

		}else {
			partiallyCondensed.add(0, fullMolecule.get(1));
			partiallyCondensed.add(0, fullMolecule.get(0));
		}
		return partiallyCondensed;
	}
	

	//This method serves to see if it is a valid molecule
	protected boolean MoleculeCheck() {
		Molecule test = new Molecule(molecularFormula);
		ArrayList<String> test_array = test.getMoleculeArray();
		
		for (int x = 0; x < test_array.size();x = x + 2) {
			
			if (test.Test(test_array.get(x)).equals("invalid element")){
				return false;
			}
		}
		return true;
	}

	
	//This method is to return the name of the molecule
	protected String MoleculeName() {
		Molecule test = new Molecule(molecularFormula);
		ArrayList<String> test_array = test.getMoleculeArray();
		
		String molecule_name = "";
		
		for (int x = 0; x < test_array.size();x = x + 2) {
			
			if (!test.Test(test_array.get(x)).equals("invalid element")){
				String name = test.getElement(test_array.get(x));
				name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
				
				molecule_name += name + " ";
			}
		}
		return molecule_name;
	}
	
	
	
	
}
