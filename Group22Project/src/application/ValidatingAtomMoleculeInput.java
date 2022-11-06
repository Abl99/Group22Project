package application;

public class ValidatingAtomMoleculeInput {
	private String errorMessage;

	boolean validateAtom(String atom) {
		//TODO check that 'atom' exists in a list of all element names and symbols
		
		return false;
	}
	
	//TODO test method
	boolean validateMolecule(String molecule) {
		boolean valid = true;
		// the first character of a molecular formula must always be a capital letter
		if (!(Character.isUpperCase(molecule.charAt(0)))) {
			valid = false;
			errorMessage = "Your molecule must begin with a capital letter. " + molecule + " is not a valid molecule.";
			return valid;
		}
		// the only allowed characters in a molecular formula are letters and numbers
		for(int index = 0; index < molecule.length(); index ++) {
			if (!Character.isLetterOrDigit(molecule.charAt(index))) {
				valid = false;
				errorMessage = "Invalid character in molecular formula: " + molecule.charAt(index);
				return valid;
			}
		}
		// there are two conditions on the sequence of uppercase letters, lowercase letters, and numbers;
		// 1) a number must be followed by another number or an uppercase letter (never a lowercase letter)
		// 2) there can only be a max of two lowercase letters in a row 
		for (int index = 0; index < molecule.length(); index ++) {
			if (Character.isDigit(molecule.charAt(index)) && index+1 < molecule.length()) {
				if (Character.isLowerCase(molecule.charAt(index+1))) {
					valid = false;
					errorMessage = "Invalid character in molecular formula; " + molecule.charAt(index+1) + " should be upper case";	
				}
			}
			if (Character.isLowerCase(molecule.charAt(index)) && index+2 < molecule.length()) {
				if (Character.isLowerCase(molecule.charAt(index+1))){
					if (Character.isLowerCase(molecule.charAt(index+2))){
						valid = false;
						errorMessage = "Invalid character sequesnce in molecular formula; " + (molecule.charAt(index-1))+
								(molecule.charAt(index))+(molecule.charAt(index+1))+(molecule.charAt(index+2))+ ".";
						return valid;
			}}}
		}
		// the elements in the molecular formula must be valid. use validateAtom method
		for (int index = 0; index < molecule.length(); index ++) {
			if (Character.isUpperCase(molecule.charAt(index)) && index+1 < molecule.length()) {
				String atom = Character.toString(molecule.charAt(index));
				boolean range = true;
				while (Character.isLowerCase(molecule.charAt(index+1)) && range == true){
					atom = atom + molecule.charAt(index+1);
					if (index+2 < molecule.length()) {
						index ++;
						range = true;
					} else range = false;
				}
				if (!validateAtom(atom)) {
					valid = false;
					errorMessage = "Invalid atom in molecular formular; " + atom + ".";
					return valid;
				}
			}
		}
		
		return valid;
	}

}
