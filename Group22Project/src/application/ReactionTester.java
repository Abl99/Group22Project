package application;

import java.util.ArrayList;

public class ReactionTester extends Reaction{

	public ReactionTester(ArrayList<Integer> reactantCoeff, ArrayList<String> reactantMolecules,
			ArrayList<Integer> productCoeff, ArrayList<String> productMolecules) {
		super(reactantCoeff, reactantMolecules, productCoeff, productMolecules);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ArrayList<Integer> RCoeff = new ArrayList<Integer> ();
		RCoeff.add(1);
		RCoeff.add(2);
		RCoeff.add(1);
		RCoeff.add(2);
		
		ArrayList<Integer> PCoeff = new ArrayList<Integer> ();
		PCoeff.add(1);
		PCoeff.add(2);
		PCoeff.add(1);
		PCoeff.add(2);
		
		ArrayList<String> RMol = new ArrayList<String>();
		RMol.add("C");
		RMol.add("C");
		RMol.add("CH3");
		RMol.add("CH3");

		ArrayList<String> PMol = new ArrayList<String>();
		PMol.add("C");
		PMol.add("C");
		PMol.add("CH3");
		PMol.add("CH3");
		
		Reaction reaction = new Reaction(RCoeff,RMol,PCoeff,PMol);
		ArrayList<String> test = reaction.condenseReaction(reaction.reactantCoeff,reaction.reactantMolecules);
		System.out.println(test);
		
		//TODO condenseReaction does not work as planned. --> troubleshoot
	}

}
