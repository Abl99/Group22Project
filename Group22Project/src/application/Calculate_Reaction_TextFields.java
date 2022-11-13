package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculate_Reaction_TextFields extends Reaction{
	
	
	public Stage applicationStage;

	//Takes all the Hboxes from the Node Array, and makes a H box Array list from them
	private ArrayList<HBox> Node_to_Hbox_Array(ArrayList<Node> node_array) {
		
		ArrayList<HBox> Hboxes = new ArrayList<HBox>();
		
		for (Node x : node_array) {
		    
	    	try {
	    		HBox y = (HBox) x;
	    		Hboxes.add(y);
	    	}catch (ClassCastException e) {
	    		continue;
	    	}
	    }
		return Hboxes;
	}
	
	//Takes all the text fields from an H box Array and makes a Text box Array list from them
	private ArrayList<TextField> Hbox_to_TextField_Array (ArrayList<HBox> Hboxes){
		
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
	    
		return my_textfields;
	}
	
	//Main method
	public void Textfield_Calculator(ArrayList<Integer> amounts, VBox main_box, VBox grams){
		//The ArrayList<Integer> amounts is so I can get the get_amount information
		
		boolean valid_input = true;
		
		//-------------------------  MOLECULES ------------------------------- 
		//ArrayList for all items in the main_box (the box where number of and molecules are)
	    ArrayList<Node> main_box_array = new ArrayList<Node>(main_box.getChildren());
	   
	    //I need to get to the H boxes then get the TextFields from them
	    ArrayList<HBox> Hboxes = Node_to_Hbox_Array(main_box_array);
	    
	    //Now I want to get the text fields from the H boxes
	    //System.out.println(Hboxes.get(0).getChildren());
	    ArrayList<TextField> my_textfields = Hbox_to_TextField_Array(Hboxes);

	    //Now I have all the text fields that I use
	    //Now I make a String Array list to hold all of the user text field inputs
	    ArrayList<String> user_inputs = new ArrayList<String>();
	    
	    for (TextField x : my_textfields) {
	    	user_inputs.add(x.getText());
	    }
	    
	    //The Array list String will now have every number of and molecule in order
	    //System.out.println(user_inputs);
	    
	    //Check if user input is a valid molecule (NOT NEEDED AT THE MOMENT)
	    /*
	    for (int x = 1; x < user_inputs.size(); x += 2) {
	    	
	    	//check if molecule or not
	    	Molecule check_input = new Molecule(user_inputs.get(x));
	    	boolean molecule_or_not = check_input.MoleculeCheck();
	    	
	    	if (!molecule_or_not) {
	    		valid_input = false;
	    	}
	    }
	    */
	    
	    //Check if user input is a number (i.e. how # of molecule)
	    for (int x = 0; x < user_inputs.size(); x += 2) {
	    	String test = user_inputs.get(x).toLowerCase().replaceAll("[0-9]", "");
	    	if (test.length() > 1 || user_inputs.get(x).isEmpty()) {
	    		valid_input = false;
	    	}
	    }
	    //I need to sort out the string array list user_inputs into the following four array lists
	    
	    ArrayList<Integer> reaction_amount_molecules = new ArrayList<Integer>();
	    ArrayList<Integer> product_amount_molecules = new ArrayList<Integer>();
	    
	    ArrayList<String> reaction_molecules = new ArrayList<String>();
	    ArrayList<String> product_molecules = new ArrayList<String>();
	    
	    System.out.println(amounts);
	    System.out.println(user_inputs);
	    
	    //for reaction_amount_molecules
	    for (int x = 0; x < amounts.get(0)*2; x += 2) {
	    	reaction_amount_molecules.add(Integer.parseInt(user_inputs.get(x)));
	    }
	    
	    //for reaction_molecules
	    for (int x = 1; x < amounts.get(0)*2; x += 2) {
	        reaction_molecules.add(user_inputs.get(x));
	    }
	    
	    //for product_amount_molecules
	    for (int x = 0; x < amounts.get(1)*2; x += 2) {
	    	product_amount_molecules.add(Integer.parseInt(user_inputs.get(x + amounts.get(0)*2)));
	    }
	    
	    //for product_molecules
	    for (int x = 1; x < amounts.get(1)*2; x += 2) {
	    	product_molecules.add(user_inputs.get(x + amounts.get(0)*2));
	    }
	    
	    System.out.println("reaction amount molecules" + reaction_amount_molecules);
	    System.out.println("reaction molecules" + reaction_molecules);
	    
	    System.out.println("----------------");
	    
	    System.out.println("product amount molecules" + product_amount_molecules);
	    System.out.println("product molecules" + product_molecules);
	    
	    //ONLY VALIDATION REMAINING AND TO CONNECT REACTIONS FOR CALCULATIONS 
	    
	   //---------------------------- GRAMS ---------------------------------- 
	    //ArrayList for all items in grams 
	    ArrayList<Node> grams_array = new ArrayList<Node>(grams.getChildren());

	    //Need to get the Hboxes from the Node ArrayList
	    ArrayList<HBox> gram_Hboxes = Node_to_Hbox_Array(grams_array);
	    
	    //Now I want to get the text fields from the H boxes
	    ArrayList<TextField> gram_textfields = Hbox_to_TextField_Array(gram_Hboxes);

	    //Now textfields to individual strings
	    ArrayList<String> gram_inputs = new ArrayList<String>();
	    
	    for (TextField x : gram_textfields) {
	    	
	    	gram_inputs.add(x.getText());
	    }
	    
	    //Check if user input is a number (i.e. how # of molecule)
	    //This may not work with decimals (may have to change how this is validated)
	    
	    for (int x = 0; x < user_inputs.size(); x += 2) {
	    	String test = user_inputs.get(x).toLowerCase().replaceAll("[0-9]", "");
	    	if (test.length() > 1 || user_inputs.get(x).isEmpty()) {
	    		valid_input = false;
	    	}
	    }
	    
	    //I need to separate the gram reactants from the gram products as a double
	    ArrayList<Double> gram_reactants = new ArrayList<Double>();
	    ArrayList<Double> gram_products = new ArrayList<Double>();
	    
	    //System.out.println(amounts);
	    
	    //This if statement is just so I can test molecules without an error
	    if (!(gram_inputs.get(0).equals(""))) {
	    	
		    for (int x = 0; x < amounts.get(0); x++) {
		    	gram_reactants.add(Double.parseDouble(gram_inputs.get(x)));	
		    }
		    
		    for (int x = 0; x < amounts.get(1); x++) {
		    	gram_products.add(Double.parseDouble(gram_inputs.get(x + amounts.get(0))));
		    }
		    
		    System.out.println("----------------");
		    
		    System.out.println("Your gram reactants are:" + gram_reactants);
		    System.out.println("Your gram products are:" + gram_products);
	    }
	    
	  //ONLY VALIDATION REMAINING AND TO CONNECT REACTIONS FOR CALCULATIONS 
	    
	  //---------------------------- ERRORS ---------------------------------- 
	    
	  if (valid_input) {
		  //need to close out of current scene
		  //System.out.println("All Valid Inputs");
		  

	  }else {
		  
		  //System.out.println("Invalid Input");
		  
		  
		  //Open new window to tell user they made an error (idea) 
		  //WORK IN PROGRESS
		  /*
		  Scene error_window = applicationStage.getScene();
		  
		  VBox error_box = new VBox();
          Label error_label = new Label("Invalid Entry");
          Button error_button = new Button("Enter");
      	  error_box.getChildren().addAll(error_label, error_button);
      	  
      	  error_button.setOnAction(doneEvent -> applicationStage.close());
      	  
      	  applicationStage.setScene(error_window);
      	  applicationStage.show();
		 */ 
		  
	  }  
	  
	  
	  
	}

	
	
}
