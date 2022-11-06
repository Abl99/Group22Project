package application;

import java.util.Arrays;

public class Molecule extends Atom{
	
	private String molecularFormula;
	private Arrays moleculeArray;
	
	Molecule (String m){
		molecularFormula = m;
	}
	
	private void getMoleculeArray() {
		//TODO separate the molecular formula in molecularFormula into 'atomic symbol'/'number of atoms' pairs
		// add each pair as an array into the moleculeArray (will have arrays within an array)
				
		//moleculeArray = 
	}
	
	private double getMolecularWeight() {
		//TODO access each array pair in moleculeArray (loop) 
		// get the atomic weight of the element (use Atom Class method)
		// multiply the element weight by the number of that element in the molecule
		// sum the results for every array pair
		
		
		return 0.0;
	}

}
