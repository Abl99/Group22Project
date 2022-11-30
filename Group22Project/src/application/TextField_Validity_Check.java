package application;

import java.util.ArrayList;

import javafx.scene.control.TextField;

//This class and its method will check if a text field is a valid input
public class TextField_Validity_Check{

	//method that checks if a text field is null, returns true if not null
	protected static boolean check_if_null(TextField to_test) {
		
		if (to_test.getText().isEmpty() || to_test.getText().equals("")) {
			return false;
		}
		return true;
	}
	
	//method that checks if a String is an int value, returns true is it is a int
	protected static boolean check_if_int(String to_test){
		
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
	
	//method that checks if a String is a double
	protected static boolean check_if_double(String to_test) {
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
	
	//This method serves to see if it is a valid molecule
	protected static boolean molecule_check(String to_test) {
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
