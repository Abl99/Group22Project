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

public class Calculate_Reaction_TextFields extends TextField_Validity_Check{
	//TextField_Validity_Check extended reaction so almost every method written so far is available 
	
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
		
		//will track the errors the user made 
		ArrayList<String> error_list = new ArrayList<String>();
		
		
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
	    	
	    	//From TextField_Validity_Check, it will return false if a text field is null and that text field will not be recorded
	    	valid_input = check_if_null(x);
	    	
	    	if (valid_input) {
	    		user_inputs.add(x.getText());
	    	}else {
	    		user_inputs.add("null value");
	    		error_list.add("1x Empty TextField in Molecules Section");
	    	}
	    }
	    //There should be no null values in ArrayList<String> user_input 
	    
	    //I need to sort out the string array list user_inputs into the following four array lists
	    
	    ArrayList<Integer> reaction_amount_molecules = new ArrayList<Integer>();
	    ArrayList<Integer> product_amount_molecules = new ArrayList<Integer>();
	    
	    ArrayList<String> reaction_molecules = new ArrayList<String>();
	    ArrayList<String> product_molecules = new ArrayList<String>();
	    
	    //for reaction_amount_molecules
	    for (int x = 0; x < amounts.get(0)*2; x += 2) {
	    	
	    	//From TextField_Validity_Check
	    	valid_input = check_if_int(user_inputs.get(x));
	    	
	    	if (valid_input) {
	    		reaction_amount_molecules.add(Integer.parseInt(user_inputs.get(x)));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x).equals("null value")) {
	    			error_list.add("1x Invalid Reaction Amount");
	    		}
	    	}
	    }
	    
	    //for reaction_molecules
	    for (int x = 1; x < amounts.get(0)*2; x += 2) {
	    	
	    	
	    	//reaction_molecules.add(user_inputs.get(x));
	    	
	    	//From TextField_Validity_Check
	    	valid_input = molecule_check(user_inputs.get(x));
	 
	    	if (valid_input) {
	    		reaction_molecules.add(user_inputs.get(x));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x).equals("null value")) {
	    			error_list.add("1x Invalid Reaction Molecule");
	    		}
	    	}
	    }
	    
	    //for product_amount_molecules
	    for (int x = 0; x < amounts.get(1)*2; x += 2) {
	    	
	    	//From TextField_Validity_Check
	    	valid_input = check_if_int(user_inputs.get(x + amounts.get(0)*2));
	    	
	    	if (valid_input) {
	    		product_amount_molecules.add(Integer.parseInt(user_inputs.get(x + amounts.get(0)*2)));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x + amounts.get(0)*2).equals("null value")) {
	    			error_list.add("1x Invalid Product Amount");
	    		}
	    	}
	    }
	    
	    //for product_molecules
	    for (int x = 1; x < amounts.get(1)*2; x += 2) {
	    	
	    	//product_molecules.add(user_inputs.get(x + amounts.get(0)*2));
	    	

	    	//From TextField_Validity_Check
	    	valid_input = molecule_check(user_inputs.get(x + amounts.get(0)*2));
	    	
	    	if (valid_input) {
	    		product_molecules.add(user_inputs.get(x + amounts.get(0)*2));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x + amounts.get(0)*2).equals("null value")) {
	    			error_list.add("1x Invalid Product Molecule");
	    		}
	    	}
	    }
	    
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
	    	
	    	valid_input = check_if_null(x);
	    	
	    	if (valid_input) {
	    		gram_inputs.add(x.getText());
	    	}else {
	    		gram_inputs.add("null value");
	    		error_list.add("1x Empty TextField in Gram Section");
	    	}
	    	
	    }
	    
	    //I need to separate the gram reactants from the gram products as a double
	    ArrayList<Double> gram_reactants = new ArrayList<Double>();
	    ArrayList<Double> gram_products = new ArrayList<Double>();
	    
		for (int x = 0; x < amounts.get(0); x++) {
			
			//From TextField_Validity_Check
			valid_input = check_if_double(gram_inputs.get(x));
			
			if (valid_input) {
				gram_reactants.add(Double.parseDouble(gram_inputs.get(x)));	
			}else {
				
				//prevent repeat errors
				if(!gram_inputs.get(x).equals("null value")) {
					error_list.add("1x Invalid Reactant Gram Amount");
				}
			}
		}
		    
		for (int x = 0; x < amounts.get(1); x++) {
			
			//From TextField_Validity_Check
			valid_input = check_if_double(gram_inputs.get(x + amounts.get(0)));
			
			if (valid_input) {
				gram_products.add(Double.parseDouble(gram_inputs.get(x + amounts.get(0))));
			}else {
				
				//prevent repeat errors
				if (!gram_inputs.get(x + amounts.get(0)).equals("null value")) {
					error_list.add("1x Invalid Product Gram Amount");					
				}
			}
		}
		    /*
		    System.out.println("----------------");
		    
		    System.out.println("Your gram reactants are:" + gram_reactants);
		    System.out.println("Your gram products are:" + gram_products);
			*/
	    
	  //ONLY VALIDATION REMAINING AND TO CONNECT REACTIONS FOR CALCULATIONS 
	    
	  //---------------------------- ERRORS ---------------------------------- 
	    
	  if (error_list.size() == 0) {
		  
		  System.out.println("You have inputted no errors");
		  
		  //This is where we will calculate if the reaction is balanced since there are no errors in the inputs
		  //All the ArrayLists should have the correct stuff in them
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  //Close out of reaction view and go back to Project_View
		  //Will need to change label on main page to say if reaction was balanced or not

	  }else {
		  
		  //Count the errors
		  ArrayList<String> accumulated_errors = new ArrayList<String>();
		  
		  //Molecule possible errors
		  int null_molecule_textfield = 0;
		  int invalid_reaction_amount = 0;
		  int invalid_product_amount = 0;
		  
		  //Gram possible errors
		  int null_gram_textfield = 0;
		  int invalid_reactant_gram_amount = 0;
		  int invalid_product_gram_amount = 0;
		  
		  //All these if statements are just for counting errors so that user gets a cleaner error message
		  for (String x : error_list) {
			  
			  if (x.equals("1x Empty TextField in Molecules Section")) {
				  null_molecule_textfield++;
				  
			  }else if (x.equals("1x Invalid Reaction Amount")) {
				  invalid_reaction_amount++;
				  
			  }else if (x.equals("1x Invalid Product Molecule")) {
				  invalid_product_amount++;
				  
			  }else if (x.equals("1x Empty TextField in Gram Section")) {
				  null_gram_textfield++;
				  
			  }else if (x.equals("1x Invalid Reactant Gram Amount")) {
				  invalid_reactant_gram_amount++;
				  
			  }else if (x.equals("1x Invalid Product Gram Amount")) {
				  invalid_product_gram_amount++;
			  } 
		  }
		  
		  if (!(null_molecule_textfield == 0)){
			  accumulated_errors.add(String.format("%dx Empty TextField in Molecules Section", null_molecule_textfield));
		  }
		  
		  if (!(invalid_reaction_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Reaction Amount", invalid_reaction_amount));
		  }
		  
		  if (!(invalid_product_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Product Molecule", invalid_product_amount));
		  }
		  
		  if (!(null_gram_textfield == 0)){
			  accumulated_errors.add(String.format("%dx Empty TextField in Gram Section", null_gram_textfield));
		  }
		  
		  if (!(invalid_reactant_gram_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Reactant Gram Amount", invalid_reactant_gram_amount));
		  }
		  
		  if (!(invalid_product_gram_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Product Gram Amount", invalid_product_gram_amount));
		  }
		  
		  //Prints the number of each error (i.e. so the user can make the proper adjustment)
		  
		  System.out.println("---------------------");
		  System.out.println("User Errors:");
		  for (String x : accumulated_errors) {
			  System.out.println(x);
		  }
		  System.out.println("---------------------");


		  //Open new window to tell user they made an error (idea) 
		  //They see their errors via the accumulated_error ArrayList
		  
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
