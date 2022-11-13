package application;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Calculate_Reaction_TextFields extends Reaction{

	//Main method
	public void Textfield_Calculator(VBox main_box, VBox grams) {
		
		//-------------------------  MOLECULES ------------------------------- 
		//ArrayList for all items in the main_box (the box where number of and molecules are)
	    ArrayList<Node> main_box_array = new ArrayList<Node>(main_box.getChildren());
	   
	    //I need to get to the H boxes then get the TextFields from them
	    ArrayList<HBox> Hboxes = new ArrayList<HBox>();
	    for (Node x : main_box_array) {
	    
	    	try {
	    		HBox y = (HBox) x;
	    		Hboxes.add(y);
	    	}catch (ClassCastException e) {
	    		continue;
	    	}
	    }
	    
	    //Now I want to get the text fields from the H boxes
	    System.out.println(Hboxes.get(0).getChildren());

	    ArrayList<TextField> my_textfields = new ArrayList<TextField>();
	    for (HBox x : Hboxes) {
	    	
	    	for (int y = 0; y < x.getChildren().size();y++) {
	    		
	    		try {
	    			my_textfields.add((TextField) x.getChildren().get(y));
		    	}catch (ClassCastException e) {
		    		continue;
		    	}
	    	}
	    }

	    //Now I have all the text fields that I use
	    //Now I make a String Array list to hold all of the user text field inputs
	    ArrayList<String> user_inputs = new ArrayList<String>();
	    
	    for (TextField x : my_textfields) {
	    	
	    	user_inputs.add(x.getText());
	    }
	    
	    System.out.println(user_inputs);
	    
	    
	    
	   //---------------------------- GRAMS ---------------------------------- 
	    //ArrayList for all items in grams 
	    ArrayList<Node> grams_array = new ArrayList<Node>(grams.getChildren());

	    //Need to get the Hboxes from the Node ArrayList
	    ArrayList<HBox> gram_Hboxes = new ArrayList<HBox>();
	    
	    
	    
	    
	    
	    
	    
	    
	}
}
