package application;

import java.util.ArrayList;

public class Reaction extends Molecule {
	// TODO encapsulate
	private ArrayList<Integer> reactantCoeff;
	private ArrayList<String> reactantMolecules;
	private ArrayList<Integer> productCoeff;
	private ArrayList<String> productMolecules;
	private ArrayList<Double> reactantMasses; 
	private ArrayList<Double> productMasses;
	private ArrayList<String> limitingReagent;

	/**
	 * 
	 * @param m
	 */
	
	//Added this so I can extend it to Calculate Reaction Text Fields 
	public Reaction() {
	}
	
	
	public Reaction(String m) {
		super(m);
		// TODO make this actually sensible
	}
	/**
	 * Constructor that takes in reactant and product coefficients and molecules
	 * @param reactantCoeff
	 * @param reactantMolecules
	 * @param productCoeff
	 * @param productMolecules
	 */
	public Reaction(ArrayList<Integer> reactantCoeff, ArrayList<String> reactantMolecules,
			ArrayList<Integer> productCoeff,ArrayList<String> productMolecules) {
		this.reactantCoeff = reactantCoeff;
		this.reactantMolecules = reactantMolecules;
		this.productCoeff = productCoeff;
		this.productMolecules = productMolecules;
		this.reactantMasses = new ArrayList<Double>();
		this.productMasses = new ArrayList<Double>();
		limitingReagent = new ArrayList<String>();
	}
	/**
	 * Constructor that takes in reactant and product coefficients and molecules, and reactant masses
	 * @param reactantCoeff
	 * @param reactantMolecules
	 * @param productCoeff
	 * @param productMolecules
	 * @param reactantMasses
	 */
	public Reaction(ArrayList<Integer> reactantCoeff, ArrayList<String> reactantMolecules,
			ArrayList<Integer> productCoeff,ArrayList<String> productMolecules,
			ArrayList<Double> reactantMasses) {
		this.reactantCoeff = reactantCoeff;
		this.reactantMolecules = reactantMolecules;
		this.productCoeff = productCoeff;
		this.productMolecules = productMolecules;
		this.reactantMasses = reactantMasses;
		this.productMasses = new ArrayList<Double>();
		limitingReagent = this.setLimitingReagent();
	}
	/**
	 * Constructor that takes in reactant and product coefficients, molecules, and masses
	 * @param reactantCoeff
	 * @param reactantMolecules
	 * @param productCoeff
	 * @param productMolecules
	 * @param reactantMasses
	 * @param productMasses
	 */
	public Reaction(ArrayList<Integer> reactantCoeff, ArrayList<String> reactantMolecules,
			ArrayList<Integer> productCoeff,ArrayList<String> productMolecules,
			ArrayList<Double> reactantMasses, ArrayList<Double> productMasses) {
		this.reactantCoeff = reactantCoeff;
		this.reactantMolecules = reactantMolecules;
		this.productCoeff = productCoeff;
		this.productMolecules = productMolecules;
		this.reactantMasses = reactantMasses;
		this.productMasses = productMasses;
		limitingReagent = this.setLimitingReagent();
	}

	ArrayList<Integer> getReactantCoeff(){
		return reactantCoeff;
	}
	void setReactantCoeff(ArrayList<Integer> reactantCoeff) {
		this.reactantCoeff = reactantCoeff;
	}
	ArrayList<String> getReactantMolecules(){
		return reactantMolecules;
	}
	void setReactantMolecules(ArrayList<String> reactantMolecules) {
		this.reactantMolecules = reactantMolecules;
	}
	ArrayList<Integer> getProductCoeff(){
		return productCoeff;
	}
	void setProductCoeff(ArrayList<Integer> productCoeff) {
		this.productCoeff = productCoeff;
	}
	ArrayList<String> getProductMolecules(){
		return productMolecules;
	}
	void setproductMolecules(ArrayList<String> productMolecules) {
		this.productMolecules = productMolecules;
	}
	ArrayList<Double> getReactantMasses(){
		return reactantMasses;
	}
	void setReactantMasses(ArrayList<Double> reactantMasses) {
		this.reactantMasses = reactantMasses;
	}
	ArrayList<Double> getProductMasses(){
		return productMasses;
	}
	void setProductMasses(ArrayList<Double> productMasses) {
		this.productMasses = productMasses;
	}
	ArrayList<String> getLimitingReagent(){
		return limitingReagent;
	}
	
	/**
	 * Method that condenses a set of molecules so that there are no repeated atoms. The indexes of the molecules and 
	 * their corresponding coefficients must align.
	 * @param coefficient; the coefficients of the molecules to be condensed
	 * @param molecules; the molecular formulas of the molecules to be condensed
	 * @return the condensed set of molecules as a array of atoms (at even indexes) and the number of atoms (at odd indexes)
	 */
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

	/**
	 * Method that checks if a chemical equation is balanced
	 * @return true if the equation is balanced and false if it is not
	 */
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
	
	/**
	 * Method evaluates the masses of the reagents and determines which one is the limiting reagent.
	 * @return an array with the molecular formula of the LR, its equivalency (in moles), and its index in 
	 * the reactantMolecules instance variable. If the reactant masses have not been assigned, an empty array is returned
	 */
	private ArrayList<String> setLimitingReagent() {
		ArrayList<String> limreag = new ArrayList<String>();
		if (!(reactantMasses.isEmpty())) {
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
				limreag.add(reactantMolecules.get(place));
				limreag.add(Double.toString(lr));
				limreag.add(Integer.toString(place));
			return limreag;
		}else {
			return limreag;
		}
	}
	
	/**
	 * Determines the theoretical yield of each product based on the limiting reagent equivalency.
	 * @return an array with the product molecular formula at even indexes and its theoretical yield 
	 * at the following odd indexes. If there is no LR, an empty array is returned
	 */
	protected ArrayList<String> theoreticalYield(){
		ArrayList<String> productTheorYield = new ArrayList<String>();
		if (!(limitingReagent.isEmpty())) {
			for (int index = 0; index < productMolecules.size();index ++) {
				Molecule mol = new Molecule(productMolecules.get(index));
				double ty = (Double.parseDouble(limitingReagent.get(1)))*productCoeff.get(0)*mol.getMolecularWeight();
				productTheorYield.add(productMolecules.get(index));
				productTheorYield.add(Double.toString(ty));
			}
		}
		return productTheorYield;
	}
	
	/**
	 * Calculates the percent yield for each product based on the theoretical yield and the given masses of products
	 * @return an array with the product molecular formula at even indexes and its percent yield 
	 * at the following odd indexes. If there is no theoretical yield or product masses, an empty array is returned
	 */
	protected ArrayList<String> yieldPercent(){
		ArrayList<String> productYieldPercent = new ArrayList<String>();
		if (!(productMasses.isEmpty()) && !(limitingReagent.isEmpty())) {
			ArrayList<String> productTheorYield = theoreticalYield();
			int count = 0;
			for (int index = 1; index < productTheorYield.size(); index = index +2) {
				productYieldPercent.add(productTheorYield.get(index-1));
				productYieldPercent.add(Double.toString(productMasses.get(count)/Double.parseDouble(productTheorYield.get(index))*100));
				count ++;
			}
		return productYieldPercent;
		}else return productYieldPercent;
	}

	public String toString() {
		String reactants = reactantCoeff.get(0)+ " " + reactantMolecules.get(0);
		String products = productCoeff.get(0) + " " + productMolecules.get(0);
		for (int index = 1; index < reactantMolecules.size(); index ++) {
			reactants = reactants + " + " + reactantCoeff.get(index)+ " " + reactantMolecules.get(index);
		}
		for (int index = 1; index < productMolecules.size(); index ++) {
			products = products + " + " + productCoeff.get(index) + " " + productMolecules.get(index);
		}
		return reactants + " --> " + products;
	}
	
	
}
