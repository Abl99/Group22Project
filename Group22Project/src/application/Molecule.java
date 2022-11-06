package application;

import java.util.ArrayList;

public class Molecule extends Atom{
	
	private String molecularFormula;
	private ArrayList<String> moleculeArray;
	
	Molecule (String m){
		molecularFormula = m;
		moleculeArray = new ArrayList<String>();
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
	
	public double getMolecularWeight() {
		//TODO test method
		double sum = 0.0;
		for (int index = 0; index < moleculeArray.size(); index = index+2) {
			Atom x = new Atom(moleculeArray.get(index));
			sum = sum + x.getAtomicWeight() * (Integer.parseInt(moleculeArray.get(index+1)));
		}
		return sum;
	}

}
