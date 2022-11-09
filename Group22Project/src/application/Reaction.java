package application;

import java.util.ArrayList;

public class Reaction extends Molecule {
	ArrayList<Integer> reactantCoeff;
	ArrayList<String> reactantMolecules;
	ArrayList<Integer> productCoeff;
	ArrayList<String> productMolecules;

	// when making an instance of Reaction, what do you need to provide?
	// ArrayList reacants = coeff, reactant (molecule), coeff, reactant,...
	// ArrayList products = coeaff, product (molecule), coeff, product,...
	public Reaction(String m) {
		super(m);
		// TODO make this actually sensible
	}
	public Reaction(ArrayList<Integer> reactantCoeff, ArrayList<String> reactantMolecules,
			ArrayList<Integer> productCoeff,ArrayList<String> productMolecules) {
		this.reactantCoeff = reactantCoeff;
		this.reactantMolecules = reactantMolecules;
		this.productCoeff = productCoeff;
		this.productMolecules = productMolecules;
	}
	
	protected ArrayList<String> condenseReaction(ArrayList<Integer> coefficient, ArrayList<String> molecules){
		ArrayList<ArrayList<String>> condensedMol = new ArrayList<ArrayList<String>>();
		for (String mol : molecules) {
			Molecule m = new Molecule(mol);
			condensedMol.add( condenseMolecule(m.getMoleculeArray()));
		}
		for (int index = 0;index < condensedMol.size();index++) {
			int coeff = coefficient.get(index);
			ArrayList<String> mol = condensedMol.get(index);
			for(int num = 1;num < mol.size();num = num+2) {
				int newnumber = (Integer.parseInt(mol.get(num)))*coeff;
				mol.set(num, Integer.toString(newnumber));		
			}
		}
		String combinedMolecule = "";
		for (ArrayList<String> mol : condensedMol) {
			combinedMolecule = combinedMolecule + mol;
		}
		Molecule c = new Molecule(combinedMolecule);
		return condenseMolecule(c.getMoleculeArray());
	}


}
