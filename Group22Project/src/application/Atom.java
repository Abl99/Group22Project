package application;

public class Atom {
	String name;
	String symbol;
	
	Atom (){}
	
	Atom (String i){
		//TODO need a way to tell if 'i' is the name or the symbol,
		// and then need to fetch the missing piece from the element data file
		
		name = i;
		symbol = i;
	}
	
	private double getAtomicWeight() {
		// TODO add a method to fetch the atomic weight from the data file
		
		return 0.0;
		
	}

}
