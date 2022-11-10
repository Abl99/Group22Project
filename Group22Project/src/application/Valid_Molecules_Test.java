package application;

public class Valid_Molecules_Test {


	public static void main(String[] args) {
		
		//For the Valid_Molecules, there needs to be at least a 1 behind every atom in the equation
		//Atoms are not case sensitive
		
		
		Valid_Molecules test = new Valid_Molecules("XE2FE3mg5H4");
		
		System.out.println(test.getMoleculeWeight());
		
		//A isn't an element
		
		Valid_Molecules test2 = new Valid_Molecules("Xe1F4AT1");
		
		System.out.println(test2.getMoleculeWeight());
	}

}
