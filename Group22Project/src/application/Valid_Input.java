package application;

//For the controller class, I am trying to check inputs for validity
//If this belongs in another class, then we can move it

public class Valid_Input{
	String message;

	public Valid_Input(String test_me){
		
		if (test_me.isBlank() || test_me.isEmpty() || test_me == null) {
			message = "No null values";
		}
		
		
		//I think I'll need to extend exceptions and or throw my own ones? 
		
		
		
		
		
		
	}
}
