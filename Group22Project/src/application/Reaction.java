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
	
	private ArrayList<String> condenseReaction(ArrayList<Integer> coeff, ArrayList<String> molecules){
		
		
		
		
		return reactantMolecules;
	}


}
