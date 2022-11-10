package application;

import java.util.ArrayList;

public class ReactionTester{
	public static void main(String[] args) {
		ArrayList<Integer> RCoeff = new ArrayList<Integer> ();
		RCoeff.add(2);
		RCoeff.add(8);
		RCoeff.add(3);
		//RCoeff.add(2);
		ArrayList<Integer> PCoeff = new ArrayList<Integer> ();
		PCoeff.add(1);
		PCoeff.add(2);
		PCoeff.add(1);
		PCoeff.add(2);
		ArrayList<String> RMol = new ArrayList<String>();
		RMol.add("BO3H3");
		RMol.add("NH4F");
		RMol.add("H2SO4");
		//RMol.add("H3C");
		ArrayList<String> PMol = new ArrayList<String>();
		PMol.add("C");
		PMol.add("C");
		PMol.add("CH3");
		PMol.add("CH3");	
		ArrayList<Double> reactMass = new ArrayList<Double>();
		reactMass.add(1.018);
		reactMass.add(2.512);
		reactMass.add(5.0);
		//reactMass.add(null);
		ArrayList<Double> prodMass = new ArrayList<Double>();
		prodMass.add(2.512);
		prodMass.add(1.018);
		prodMass.add(5.0);
		//reactMass.add(null);
		
		Reaction reaction = new Reaction(RCoeff,RMol,PCoeff,PMol,reactMass,prodMass);
		ArrayList<String> test = reaction.condenseReaction(reaction.reactantCoeff,reaction.reactantMolecules);
		System.out.println(test);
		
		ArrayList<String> test2 = reaction.condenseReaction(reaction.productCoeff,reaction.productMolecules);
		System.out.println(test2);

		boolean balanced = reaction.balancedReaction();
		System.out.println(balanced);
		
		System.out.println(reaction.limitingReagent);
	}

}
