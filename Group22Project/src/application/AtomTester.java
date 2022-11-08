package application;

public class AtomTester {

	public static void main(String[] args) {
		Atom m = new Atom("Bismuth");
		System.out.println(m.getName() + "name");
		System.out.println(m.getSymbol() + "symbol");

		System.out.println(m.fetchNameOrSymbol(m.getName()));
		System.out.println(m.fetchNameOrSymbol(m.getSymbol()));
		
		double weight = m.getAtomicWeight();
		System.out.println(weight);
		
		//boolean p = Atom.elementExistence("Hydrogen");
		//System.out.println(p);


	}

}
