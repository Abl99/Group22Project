package application;

import java.util.ArrayList;

import javafx.scene.control.TextField;



//This class and its method will check if a text field is a valid input
public class TextField_Validity_Check{
	
	/**
	 * Checks if a textfield is null
	 * 
	 * @param to_test: the value of a given textfield
	 * @return boolean: will return false if the textfield is null/empty. Will cause errors if we allow for null information
	 * to pass through 
	 */

	//method that checks if a text field is null, returns true if not null
	protected boolean check_if_null(TextField to_test) {
		
		if (to_test.getText().isEmpty() || to_test.getText().equals("")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if a textfield is an int or not.
	 * 
	 * @param to_test: the value of a given textfield
	 * @return boolean: will return false if the textfield is not an int. We determine this by trying to convert a string to an int
	 * if the convert fails, then it isn't an int and if it succeeds but the value is less than 0, then it returns false since we don't
	 * accept negative numbers
	 */
	
	//method that checks if a String is an int value, returns true is it is a int
	protected boolean check_if_int(String to_test){
		
		try {
			
			int test = Integer.parseInt(to_test);
			
			//no negative values
			if (test < 0) {
				return false;
			}
			
		}catch (NumberFormatException e) {
			
			return false;
			
		}
		return true;
		
	}
	
	/**
	 * Checks if a textfield is a double or not.
	 * 
	 * @param to_test: the value of a given textfield
	 * @return boolean: will return false if not a double. If it is a double but negative, it will return false as well. 
	 */
	
	//method that checks if a String is a double
	protected boolean check_if_double(String to_test) {
		try {
			
			Double test = Double.parseDouble(to_test);
			
			//no negative values
			if (test < 0) {
				return false;
			}
			
		}catch (NumberFormatException e) {
			
			return false;
			
		}
		return true;
	}
	
	/**
	 * Checks if a textfield is a valid molecule or not.
	 * 
	 * @param to_test: the value of a given textfield
	 * @return boolean: will return false if it is an invalid molecule. If it is a molecule, then it will return true. 
	 */
	
	//This method serves to see if it is a valid molecule
	protected boolean molecule_check(String to_test) {
		Molecule test = new Molecule(to_test);
		ArrayList<String> test_array = test.getMoleculeArray();
		
		for (int x = 0; x < test_array.size();x = x + 2) {
			
			if (test.Test(test_array.get(x)).equals("invalid element")){
				return false;
			}
		}
		return true;
	}
	

	
}
