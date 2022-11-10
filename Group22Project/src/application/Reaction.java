package application;

import java.util.ArrayList;

public class Reaction extends Molecule {
	// TODO encapsulate
	ArrayList<Integer> reactantCoeff;
	ArrayList<String> reactantMolecules;
	ArrayList<Integer> productCoeff;
	ArrayList<String> productMolecules;
	ArrayList<Double> reactantMasses; 
	ArrayList<Double> productMasses;
	String limitingReagent;

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
		this.reactantMasses = new ArrayList<Double>();
		this.productMasses = new ArrayList<Double>();
		limitingReagent = "";
	}
	
	public Reaction(ArrayList<Integer> reactantCoeff, ArrayList<String> reactantMolecules,
			ArrayList<Integer> productCoeff,ArrayList<String> productMolecules,
			ArrayList<Double> reactantMasses, ArrayList<Double> productMasses) {
		this.reactantCoeff = reactantCoeff;
		this.reactantMolecules = reactantMolecules;
		this.productCoeff = productCoeff;
		this.productMolecules = productMolecules;
		this.reactantMasses = reactantMasses;
		this.productMasses = productMasses;
		limitingReagent = (this.getLimitingReagent()).get(0);
	}
	
	protected ArrayList<String> condenseReaction(ArrayList<Integer> coefficient, ArrayList<String> molecules){
		ArrayList<ArrayList<String>> condensedMol = new ArrayList<ArrayList<String>>();
		for (String mol : molecules) {
			Molecule m = new Molecule(mol);
			condensedMol.add(m.getMoleculeArray());
		}
		// each molecule needs to be multiplied by its coefficient (which is the number of that molecule)
		// to get the correct number of each atom in that molecule. Then everything is added to a single string.
		String reactionstring = "";
		for (int index = 0;index < condensedMol.size();index++) {
			int coeff = coefficient.get(index);
			ArrayList<String> mol = condensedMol.get(index);
			for(int num = 1;num < mol.size();num = num+2) {
				int newnumber = (Integer.parseInt(mol.get(num)))*coeff;
				mol.set(num, Integer.toString(newnumber));
				reactionstring = reactionstring + mol.get(num-1) + mol.get(num);
			}
		}
		Molecule c = new Molecule(reactionstring);
		return condenseMolecule(c.getMoleculeArray());
	}

	protected boolean balancedReaction() {
		ArrayList<String> reactants = new ArrayList<String>(condenseReaction(reactantCoeff,reactantMolecules));
		ArrayList<String> products = new ArrayList<String>(condenseReaction(productCoeff,productMolecules));
		for (int index = 0; index < reactants.size();index = index +2) {
			int num = products.indexOf(reactants.get(index));
			if (num >= 0 ) {
				if (!(reactants.get(index+1).equals(products.get(num+1)))) return false;
			} else return false;
		}
		return true;
	}
	
	// returns an arraylist of [molecule, equivalency, index of its place in the instance variable arraylists]
	private ArrayList<String> getLimitingReagent() {
		int place = -1;
		double lr = 0.0;
		for (int index = 0; index < reactantMolecules.size();index ++) {
			Molecule mol = new Molecule(reactantMolecules.get(index));
			double equivalency = reactantMasses.get(index)/(mol.getMolecularWeight() * reactantCoeff.get(index));
			if (lr == 0.0 || equivalency < lr) {
				lr = equivalency;
				place = index;
			}
		}
		ArrayList<String> limreag = new ArrayList<String>();
			limreag.add(reactantMolecules.get(place));
			limreag.add(Double.toString(lr));
			limreag.add(Integer.toString(place));
		return limreag;
	}

	
	
	
}
