package application;

import java.util.ArrayList;

public class MoleculeTester {

	public static void main(String[] args) {
		String input = "XeF4";
		Molecule m = new Molecule(input);
		
		String p = m.getMolecularFormula();
		ArrayList<String> q = m.getMoleculeArray();
		double r = m.getMolecularWeight();
		
		System.out.println(p);
		System.out.println(q);
		System.out.println(r);	
		
	}

}
